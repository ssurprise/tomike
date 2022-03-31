package com.skx.common.permission

interface PermissionError {

    fun onErrorEvent(errorCode: Int, errorMsg: String)

    companion object {
        const val ERROR_CODE_PERMISSION_EMPTY: Int = 1
        const val ERROR_CODE_CONTEXT_NOT_FIND: Int = 2
        const val ERROR_CODE_UNSUPPORT_CONTEXT: Int = 3
    }
}