package com.luoyuer.lrc

import java.io.File
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

/**
 * @param lrcFile 歌词文件
 * @param wordstep 按字出歌词
 */
data class Lrc(
    val lrcFile:File,
    val wordstep:Boolean = true
){
    data class SingleLrc(val startTime:Long,val endTime:Long,val txt:String)
    private val lrcs = mutableListOf<SingleLrc>()
    private var title:String? = null
    private var article:String? = null
    private var al:String? = null
    private var by:String? = null
    private var offset:Long = 0
    init {
        val readLines = lrcFile.readLines(charset = Charset.forName("GB2312"))
        var begin = true
        for ((index, item) in readLines.withIndex()) {
            if (!item.startsWith("[")){
                //如果不是[开头，说明空行，跳过
                break
            }
            if (begin){
                if (item.startsWith("[00:")){ //代表已经开始正式歌词
                    begin = false
                }
                if (item.startsWith("[ti")){
                    item.split(":")[1].let {
                        title = it.substring(0,it.length-1)
                    }
                }
                if (item.startsWith("[ar")){
                    item.split(":")[1].let {
                        article = it.substring(0,it.length-1)
                    }
                }
                if (item.startsWith("[al")){
                    item.split(":")[1].let {
                        al = it.substring(0,it.length-1)
                    }
                }
                if (item.startsWith("[by")){
                    item.split(":")[1].let {
                        by = it.substring(0,it.length-1)
                    }
                }
                if (item.startsWith("[offset")){
                    item.split(":")[1].let {
                        try{
                            offset = it.substring(0,it.length-1).toLong()
                        }catch (_:Exception){}
                    }
                }
            }
            //这里不用else是因为真的要再判断一次
            if (!begin){

                val (time,text) = readLrcInfo(item)
                //获取下一句歌词时间
                var nextTime = 0L
                if (index == readLines.size-1 || !readLines[index+1].startsWith("[")){
                    //如果到达文件尾
                    //获取上一句歌词长度，估算
                    if (lrcs.isEmpty()){
                        nextTime = time
                    }else{
                        val last = lrcs.last()
                        nextTime = time+(((last.endTime - last.startTime)/(last.txt.length))*text.length)
                    }
                }else{
                    val (nTime, _) = readLrcInfo(readLines[index+1])
                    nextTime = nTime
                }
                lrcs+=SingleLrc(time+offset,nextTime+offset,text)
            }
        }
    }
    fun getText(time:Long):String{
        if (lrcs.isEmpty())return ""
        var currLrc = lrcs.last()
        for (lrc in lrcs) {
            if (time>=lrc.startTime && time<lrc.endTime){
                currLrc = lrc
                break
            }
        }
        if (wordstep){
            val safeEnd = currLrc.endTime - (currLrc.endTime - currLrc.startTime)*0.5
            //计算播放的字
            if (time > safeEnd)return currLrc.txt
            val off = (time - currLrc.startTime)
            if (off == 0L) return ""
            val rate = off.toDouble()/(safeEnd - currLrc.startTime)
            return currLrc.txt.substring(0, Math.round(currLrc.txt.length*rate).toInt())
        }else{
            return currLrc.txt
        }
    }
    private fun readLrcInfo(sourceText:String):Pair<Long,String>{
        val info = sourceText.split("]")
        val time = info[0].substring(1)
        val text = info[1]
        val minu = time.substring(0,2).toLong()
        val sec = time.substring(3,5).toLong()
        var ms = 0L
        //毫秒非必选
        if (time.length >=6){
            ms = time.substring(6,8).toLong()*10
        }
        return minu*60*1000+sec*1000+ms to text
    }
}
