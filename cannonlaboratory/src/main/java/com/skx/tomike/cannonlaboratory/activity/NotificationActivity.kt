package com.skx.tomike.cannonlaboratory.activity

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import com.skx.tomike.cannonlaboratory.R


class NotificationActivity : AppCompatActivity() {


    private var channel_id = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)


        val button = findViewById<Button>(R.id.btn_notification_create)
        val button2 = findViewById<Button>(R.id.btn_notification_cancel)

        // 构建通知管理器
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        button.setOnClickListener { v: View? -> createNotification() }

        button2.setOnClickListener { View.OnClickListener { v: View? -> cancelNotification() } }
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun createNotification() {

        val manager = NotificationManagerCompat.from(this)
        createNotificationChannel()

        channel_id++

        Log.e("channel_id", channel_id.toString())

        val builder = NotificationCompat.Builder(this, "com.skx.tomike")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("狗子狗子")
                .setContentText("呼叫卢狗子，你亲爱的师狗子找你ninininininiinininininininininininnnnnnnnnn")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)


        manager.notify(channel_id, builder.build())
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "加农炮"//R.string.channel_name
            val descriptionText = "channel_description"//  getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            // channel_id 必须一样才可以发送成功，要不然通知不生效
            val channel = NotificationChannel(channel_id.toString(), name, importance).apply {
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
