package com.skx.tomike.cannon.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ui.AppUtils
import com.skx.tomike.cannon.ui.ChannelUtils
import com.tencent.tinker.lib.tinker.Tinker
import com.tencent.tinker.lib.tinker.TinkerInstaller

/**
 * 描述 : 热修复 - 微信Tinker
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/1/21 6:08 PM
 */
class HotfixActivity : SkxBaseActivity<BaseViewModel>(), View.OnClickListener {

    private var mPatchDir: String? = null

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("热修复 - 微信Tinker").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_hotfix_tinker
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        findViewById<View>(R.id.btn).setOnClickListener(this)
//        findViewById<TextView>(R.id.tv_content).text = "这是有问题的文案"
        findViewById<TextView>(R.id.tv_content).text = "已通过java修复-这是没问题的文案"
        findViewById<View>(R.id.showInfo).setOnClickListener {
            showInfo(it.context)
        }
        findViewById<TextView>(R.id.tv_channel).apply {
            text = "当前渠道为：${
                ChannelUtils.getChannel(
                    this.context,
                    "主渠道"
                )
            }"
        }
        findViewById<TextView>(R.id.tv_package_name).apply {
            text = "包名：${AppUtils.getInstance().appPackage}"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        askForRequiredPermissions()
        makeDir()
    }

    override fun onClick(v: View?) {
        loadPatch()
    }

    /**
     * 创建保存patch包的文件夹
     */
    private fun makeDir() {
        // /sdcard/tpatch
        mPatchDir = Environment.getExternalStorageDirectory().absolutePath
        Log.e("hotfix-tinker", "path:$mPatchDir")
    }


    /**
     * 动态申请权限
     */
    private fun askForRequiredPermissions() {
        if (Build.VERSION.SDK_INT < 23) {
            return
        }
        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                0
            )
        }
    }

    private fun hasRequiredPermissions(): Boolean {
        return if (Build.VERSION.SDK_INT >= 16) {
            val res = ContextCompat.checkSelfPermission(
                this.applicationContext,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            res == PackageManager.PERMISSION_GRANTED
        } else {
            // When SDK_INT is below 16, READ_EXTERNAL_STORAGE will also be granted if WRITE_EXTERNAL_STORAGE is granted.
            val res = ContextCompat.checkSelfPermission(
                this.applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            res == PackageManager.PERMISSION_GRANTED
        }
    }

    /**
     * 加载patch包
     */
    private fun loadPatch() {
        val patch: String =
            Environment.getExternalStorageDirectory().absolutePath + "/patch_signed.apk"
        if (Tinker.isTinkerInstalled()) {
            Log.e("hotfix-tinker", "loadPath:$patch")
            TinkerInstaller.onReceiveUpgradePatch(applicationContext, patch)
        }
    }

    private fun showInfo(context: Context?): Boolean {
        // add more Build Info
//        val sb = StringBuilder()
//        val tinker = Tinker.with(applicationContext)
//        if (tinker.isTinkerLoaded) {
//            sb.append(String.format("[patch is loaded] \n"))
//            sb.append(
//                java.lang.String.format(
//                    "[buildConfig TINKER_ID] %s \n",
//                    BuildInfo.TINKER_ID
//                )
//            )
//            sb.append(
//                java.lang.String.format(
//                    "[buildConfig BASE_TINKER_ID] %s \n",
//                    "1.0"
//                )
//            )
//            sb.append(
//                java.lang.String.format(
//                    "[buildConfig MESSSAGE] %s \n",
//                    BuildInfo.MESSAGE
//                )
//            )
//            sb.append(
//                String.format(
//                    "[TINKER_ID] %s \n",
//                    tinker.tinkerLoadResultIfPresent.getPackageConfigByName(ShareConstants.TINKER_ID)
//                )
//            )
//            sb.append(
//                String.format(
//                    "[packageConfig patchMessage] %s \n",
//                    tinker.tinkerLoadResultIfPresent.getPackageConfigByName("patchMessage")
//                )
//            )
//            sb.append(String.format("[TINKER_ID Rom Space] %d k \n", tinker.tinkerRomSpace))
//        } else {
//            sb.append(String.format("[patch is not loaded] \n"))
//            sb.append(
//                java.lang.String.format(
//                    "[buildConfig TINKER_ID] %s \n",
//                    BuildInfo.TINKER_ID
//                )
//            )
//            sb.append(
//                java.lang.String.format(
//                    "[buildConfig BASE_TINKER_ID] %s \n",
//                    "1.0"
//                )
//            )
//            sb.append(
//                java.lang.String.format(
//                    "[buildConfig MESSSAGE] %s \n",
//                    BuildInfo.MESSAGE
//                )
//            )
//            sb.append(
//                String.format(
//                    "[TINKER_ID] %s \n", ShareTinkerInternals.getManifestTinkerID(
//                        applicationContext
//                    )
//                )
//            )
//        }
//
//        val v = TextView(context)
//        v.text = sb
//        v.gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
//        v.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10f)
//        v.layoutParams = ViewGroup.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
//        v.setTextColor(-0x1000000)
//        v.setTypeface(Typeface.MONOSPACE)
//        val padding = 16
//        v.setPadding(padding, padding, padding, padding)
//        val builder = AlertDialog.Builder(context)
//        builder.setCancelable(true)
//        builder.setView(v)
//        val alert = builder.create()
//        alert.show()
        return true
    }
}