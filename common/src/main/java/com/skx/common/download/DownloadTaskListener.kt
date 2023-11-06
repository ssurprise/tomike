package com.skx.common.download

interface DownloadTaskListener {

    fun onStart()
    fun onPause()
    fun onRunning(progress: Int)

    fun onCompleted()
}