package com.skx.common.permission

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * 描述 : 权限
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:21 PM
 */
class PermissionFragment : Fragment() {

    private var mContext: Context? = null

    /**
     * 申请的权限
     */
    private var mPermissions: Array<String>? = null
    private var mActivityResultLauncher: ActivityResultLauncher<Array<String>>? = null
    private val mResult = mutableMapOf<String, Boolean>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams()
        reqPermissions()
    }

    private fun initParams() {
        arguments?.takeIf {
            it.containsKey(KEY_PERMISSION)
        }?.run {
            mPermissions = this.getStringArray(KEY_PERMISSION)
        }

        mActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result: Map<String?, Boolean?> ->
            result.forEach {
                it.key?.run {
                    Log.d(TAG, "授权结果: ${it.key}->${it.value}")
                    mResult[this] = it.value ?: false
                }
            }
        }
    }

    private fun reqPermissions() {
        if (Build.VERSION.SDK_INT < 23) {
            Toast.makeText(mContext, "6.0 以下", Toast.LENGTH_SHORT).show()
            return
        }
        if (mPermissions == null || mPermissions!!.isEmpty()) return

        val needRequest = mutableListOf<String>()
        mPermissions?.forEach {
            if (!checkPermission(it)) {
                mResult[it] = false
                needRequest.add(it)
            } else {
                mResult[it] = false
            }
        }

        // 请求权限处理
        if (needRequest.size > 0) {
            val permissions = needRequest.toTypedArray()
            Log.d(TAG, "需要申请的权限: $permissions")
            mActivityResultLauncher?.launch(permissions)
        }
    }

    private fun checkPermission(permission: String): Boolean {
        val result = ContextCompat.checkSelfPermission(
            mContext!!,
            permission
        ) == PackageManager.PERMISSION_GRANTED
        Log.d(TAG, "checkPermission: ${permission}->${result}")
        return result
    }


    companion object {
        private const val TAG = "PermissionUtils"
        private const val KEY_PERMISSION = "request_permissions"

        @JvmStatic
        fun getInstance(permissions: Array<String>?): PermissionFragment {
            val fragment = PermissionFragment()
            val bundle = Bundle()
            bundle.putStringArray(KEY_PERMISSION, permissions)
            fragment.arguments = bundle
            return fragment
        }
    }
}