package com.skx.common.permission

import android.content.Context
import androidx.fragment.app.FragmentManager


/**
 * 描述 : 权限参数类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:19 PM
 */
class PermissionParams {

    var mContext: Context? = null
    var mCallback: PermissionResultListener? = null
    var mReqPermissionTip: ReqPermissionTip? = null
    var mPermissions: Array<String>? = null
    var mFragmentManager: FragmentManager? = null
    var mError: PermissionError? = null

}