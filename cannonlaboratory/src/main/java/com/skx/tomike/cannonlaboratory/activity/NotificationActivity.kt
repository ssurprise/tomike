package com.skx.tomike.cannonlaboratory.activity

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.skx.tomike.cannonlaboratory.R


class NotificationActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)


        val button = findViewById<Button>(R.id.btn_notification_create)
        val button2 = findViewById<Button>(R.id.btn_notification_cancel)

        // 构建通知管理器
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        button.setOnClickListener { v: View? -> createNotification(notificationManager) }

        button2.setOnClickListener { View.OnClickListener { v: View? -> cancelNotification(notificationManager) } }
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    fun createNotification(notificationManager: NotificationManager) {

//        val notificationCompat: NotificationCompat =
        // 构建notification
        val build: Notification.Builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, "")
        } else {
            Notification.Builder(this)
        }

        val notification = build.setContentText("我是content，我是content")
                .setContentTitle("我是title，我是title")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build()

        var builder = NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("我是title，我是title")
                .setContentText("我是content，我是content")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        notificationManager.notify(1000, notification)
    }

    fun cancelNotification(notificationManager: NotificationManager) {
        notificationManager.cancelAll()
    }
}
