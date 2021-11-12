package com.skx.tomike.cannon.ui.activity

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTER_GROUP
import com.skx.tomike.cannon.ROUTE_PATH_notification

@Route(path = ROUTE_PATH_notification, group = ROUTER_GROUP)
class NotificationActivity : SkxBaseActivity<BaseViewModel>() {

    private var mChannelId = 1
    private var mSwitchNotification: SwitchCompat? = null

    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("通知权限").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_notification
    }

    override fun initView() {
        val button = findViewById<Button>(R.id.btn_notificationManager_create)
        val button2 = findViewById<Button>(R.id.btn_notificationManager_cancel)

        mSwitchNotification = findViewById(R.id.sc_notificationManager_switch)

        button.setOnClickListener { createNotification() }
        button2.setOnClickListener { View.OnClickListener { cancelNotification() } }

        mSwitchNotification?.isChecked = isPermissionOpen(this)
        mSwitchNotification?.setOnClickListener {
            openNotificationSetting()
        }
    }

    override fun onRestart() {
        super.onRestart()
        mSwitchNotification?.isChecked = isPermissionOpen(this)
    }

    /**
     * 打开通知设置页
     */
    private fun openNotificationSetting() {
        val localIntent = Intent()
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {//API 26 8.0以上版本
                localIntent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                localIntent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {//API 21 5.0 以上版本
                localIntent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
                localIntent.putExtra("app_package", packageName)
                localIntent.putExtra("app_uid", applicationInfo.uid)
            }
            else -> {
                localIntent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                localIntent.addCategory(Intent.CATEGORY_DEFAULT)
                localIntent.data = Uri.parse("package:$packageName")
            }
        }
        startActivity(localIntent)
    }

    /**
     * 检测是否开启通知权限
     *
     * @param context 上下文
     */
    private fun isPermissionOpen(context: Context?): Boolean {
        if (context == null) return false
        return NotificationManagerCompat.from(context).areNotificationsEnabled()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationManagerCompat.from(context).importance != NotificationManager.IMPORTANCE_NONE
//        } else {

//        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun createNotification() {
        val manager = NotificationManagerCompat.from(this)
        createNotificationChannel()
        mChannelId++
        Log.e("channel_id", mChannelId.toString())
        val builder = NotificationCompat.Builder(this, "com.skx.tomike")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("狗子狗子")
            .setContentText("呼叫狗子，ninininininiinininininininininininnnnnnnnnn")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
        manager.notify(mChannelId, builder.build())
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "加农炮"//R.string.channel_name
            val descriptionText = "channel_description"//  getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            // channel_id 必须一样才可以发送成功，要不然通知不生效
            val channel = NotificationChannel(mChannelId.toString(), name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun cancelNotification() {
//        notificationManager.cancelAll()
    }
}
