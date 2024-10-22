package com.luoyuer.player

/**
 * 全局单例使用Player
 */
public interface Player {
    /**
     * 设置要播放的歌曲。若当前正在播放，停止，开始从头播放新设置的歌曲
     */
    public fun setSong(song: Song)

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
    public fun onPlayStateChange(callback: (PlayStateEnum)->Unit)

    /**
     * 播放进度改变时的回调
     */
    public fun onTimeChange(callback:(Long)-> Unit)
}