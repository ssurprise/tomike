package com.skx.common.permission

import android.util.Log

object PermLog {

    private const val TAG = "PermissionController"

    fun d(msg: String) {
        Log.d(TAG, msg)
    }
}