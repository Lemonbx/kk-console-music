package com.luoyuer.player

public enum class PlayStateEnum {
    STOPPED, //已停止（默认状态）
    LOADING,//正在加载歌曲（比如网络歌曲等待io）
    LOADFAILED,//歌曲加载失败
    PAUSED,//暂停播放
    PLAYING,//正在播放
    COMPLETED,//已播放完成
}