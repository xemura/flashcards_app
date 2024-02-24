package com.xenia.englishusingflashcards.alarm_manager

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Icon
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.xenia.englishusingflashcards.R

class AlarmReceiver : BroadcastReceiver() {

    private var notificationManager: NotificationManagerCompat? = null

    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d("Notification", "onReceive")

        notificationManager = NotificationManagerCompat.from(context!!)
        val notification = NotificationCompat.Builder(context, "notify")
            .setContentTitle("Время тренировки!")
            .setContentText("Давно тебя не было! Самое время повторить слова.")
            .setSmallIcon(R.drawable.logo)
            .setLargeIcon(Icon.createWithResource(context, R.drawable.logo))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager?.notify(1, notification)
    }
}