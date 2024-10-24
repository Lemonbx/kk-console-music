package com.luoyuer.player

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

public abstract class BasePlayer : Player {

    override var state: PlayStateEnum = PlayStateEnum.STOPPED
        set(value) {
            onPlayStateChange(field, value)
            field = value
        }

    override var song: Song = EmptySong
    set(value) {
        field = value
    }

    private var onPlayStateChange: (old: PlayStateEnum, new:  PlayStateEnum) -> Unit = {_, _ ->}
    private var pnTimeChange: (Long) -> Unit = {}

    override fun addOnPlayStateChangeListener(callback: (PlayStateEnum, PlayStateEnum) -> Unit) {
        val old = onPlayStateChange
        onPlayStateChange = { oldS, newS ->
            old(oldS, newS)
            callback(oldS, newS)
        }
    }

    override fun addOnTimeChangeListener(callback: (Long) -> Unit) {
        val old = pnTimeChange
        pnTimeChange = {
            old(it)
            callback(it)
        }
    }

    override fun play() {
        when (state) {
            PlayStateEnum.STOPPED -> startNewPlay()
            PlayStateEnum.LOADING -> TODO()
            PlayStateEnum.LOADFAILED -> TODO()
            PlayStateEnum.PAUSED -> resumePlay()
            PlayStateEnum.PLAYING -> {}
            PlayStateEnum.COMPLETED -> startNewPlay()
        }
    }

    protected abstract fun startNewPlay()

    protected abstract fun resumePlay()

}

public abstract class KKPlayer : BasePlayer(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        = SupervisorJob() + Dispatchers.Default

    private var songCont: Continuation<Unit>? = null
    private var playJob: Job? = null



    protected abstract val pcmFlow: Flow<ByteArray>

    override fun resumePlay() {
        val count = songCont ?: throw IllegalStateException("No count")
        count.resume(Unit)
        songCont = null
    }

    override fun startNewPlay() {
        playJob?.cancel()
        songCont = null
        playJob = launch {
            pcmFlow.collect {
                checkPlay()
                // send data to device
            }
        }
    }

    private suspend fun checkPlay() {
        when (state) {
            PlayStateEnum.STOPPED -> stopPlay()
            PlayStateEnum.LOADING -> waitLoading()
            PlayStateEnum.LOADFAILED -> TODO()
            PlayStateEnum.PAUSED -> pausePlay()
            PlayStateEnum.PLAYING -> {}
            PlayStateEnum.COMPLETED -> stopPlay()
        }
    }

    private fun stopPlay() {
        throw CancellationException("Stop playing")
    }

    private suspend fun waitLoading() {

    }

    private suspend fun pausePlay() {
        suspendCoroutine<Unit> {
            songCont = it
        }
    }
}