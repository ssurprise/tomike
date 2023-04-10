package com.skx.tomike.cannon.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.common.permission.*
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_PERMISSION
import com.skx.tomike.cannon.bean.Permission

/**
 * 6.0 权限介绍
 */
@Route(path = ROUTE_PATH_PERMISSION)
class PermissionIntroActivity : SkxBaseActivity<BaseViewModel?>() {

    private val mTvLogcat by lazy {
        findViewById<TextView>(R.id.tv_permission_logcat)
    }

    private val mAdapter = PermissionAdapter().apply {
        initPermissions(PERMISSIONS)
    }

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("权限管理").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_permission_intro
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        findViewById<RecyclerView>(R.id.rv_permission_items).apply {
            layoutManager = GridLayoutManager(this@PermissionIntroActivity, 2)
            adapter = mAdapter
        }
    }

    @SuppressLint("SetTextI18n")
    fun onByPressed(view: View?) {
        if (mAdapter.getCheckedPermission().size == 0) {
            mTvLogcat.append("当前没有要申请的权限\n")
            Log.e(TAG, "当前没有要申请的权限")
            return
        }
        if (Build.VERSION.SDK_INT < 23) {
            mTvLogcat.text = "6.0以下无需动态申请权限\n"
            Log.e(TAG, "6.0以下无需动态申请权限")
            return
        }

        val permissions = mAdapter.getCheckedPermission()
        permissions.forEach {
            mTvLogcat.append("申请权限： $it \n")
            Log.e(TAG, "申请权限： $it")
        }

        PermissionController.Builder(this)
                .permissions(permissions)
                .associateDefaultTip()
                .callback(object : PermissionResultListener {
                    override fun onSucceed(grantPermissions: List<String>?) {
                        mTvLogcat.append("目标权限已授权\n\n")
                    }

                    override fun onFailed(deniedPermissions: List<String>?) {
                        deniedPermissions?.forEach {
                            mTvLogcat.append("$it 授权拒绝\n")
                        }
                        mTvLogcat.append("\n")
                    }
                })
                .request()
    }

    companion object {
        val PERMISSIONS = mutableListOf(
                // 获取手机状态（包括手机号码、IMEI、IMSI权限等）
                Permission(Manifest.permission.READ_PHONE_STATE, "获取手机状态"),
                Permission(Manifest.permission.ACCESS_FINE_LOCATION, "定位权限"),
                Permission(Manifest.permission.CAMERA, "摄像头权限"),
                Permission(Manifest.permission.READ_EXTERNAL_STORAGE, "读权限"),
                Permission(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写权限"),
                Permission(Manifest.permission.SYSTEM_ALERT_WINDOW, "悬浮窗"),
                Permission(Manifest.permission.WRITE_SETTINGS, "系统设置"),
                Permission(Manifest.permission.PACKAGE_USAGE_STATS, "使用情况访问权限")
        )
    }

    class PermissionAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private val mPermissionList = mutableListOf<Permission>()
        private val mCheckMap = mutableMapOf<String, Int>()

        fun initPermissions(permission: MutableList<Permission>) {
            mPermissionList.clear()
            mPermissionList.addAll(permission)
        }

        fun getCheckedPermission(): MutableList<String> {
            val checked = mutableListOf<String>()
            mCheckMap.forEach {
                checked.add(it.key)
            }
            return checked
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return PermissionViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.adapter_permission_request, parent, false)
            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is PermissionViewHolder) {
                val permission = mPermissionList[position]
                holder.tvPermissionName.text = permission.name
                holder.tvPermissionName.isChecked = mCheckMap.containsKey(permission.key)
                holder.itemView.setOnClickListener {
                    if (mCheckMap.containsKey(permission.key)) {
                        mCheckMap.remove(permission.key)
                    } else {
                        mCheckMap[permission.key] = position
                    }
                    notifyItemChanged(position)
                }
            }
        }

        override fun getItemCount(): Int {
            return mPermissionList.size
        }

        class PermissionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvPermissionName: CheckBox = itemView.findViewById(R.id.iv_permission_name)
        }
    }
}