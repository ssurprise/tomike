package com.skx.common.download

/**
 * @Author： shiguotao
 * @E-mail: shiguotao-os@360os.com
 * @DATE： 2023/10/13 17:38
 * @Description： 下载任务
 */
class Task(val url: String, val localPath: String, val fileName: String) {

    private var progress: Int = 0

    var downloadStatus = Status.WAITING
}


enum class Status {
    WAITING, LOADING, PAUSE, OVER, FAILED
}