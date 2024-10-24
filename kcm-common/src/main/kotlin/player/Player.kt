package com.luoyuer.player

/**
 * 全局单例使用Player
 */
public interface Player {
    /**
     * 正在播放的歌曲。若当前正在播放，停止，开始从头播放新设置的歌曲
     */
    public var song: Song

    public val state: PlayStateEnum

    /**
     * 播放歌曲
     */
    public fun play()

    /**
     * 暂停播放（记录当前进度）
     */
    public fun pause()

    /**
     * 停止播放（进度设置为0）
     */
    public fun stop()

    /**
     * 跳转到指定位置（维持当前播放状态）
     */
    public fun jumpTo(time: Long)

    /**
     * 播放状态切换时的回调
     */
    public fun addOnPlayStateChangeListener(callback: (old: PlayStateEnum, new: PlayStateEnum) -> Unit)

    /**
     * 播放进度改变时的回调
     */
    public fun addOnTimeChangeListener(callback:(Long) -> Unit)
}