package com.sanya1am.profile.presentation.receiver

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.ComponentActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.sanya1am.profile.R

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

        val text = intent.getStringExtra("NOTIFICATION").orEmpty()

        notificationManager.sendNotification(
            messageTitle = "Сработало напоминание",
            messageBody = text,
            applicationContext = context,
        )
    }

    private fun NotificationManager.sendNotification(
        messageTitle: String,
        messageBody: String,
        applicationContext: Context
    ) {
        val activityIntent = Intent(applicationContext, ComponentActivity::class.java)

        val builder = NotificationCompat.Builder(
            applicationContext,
            applicationContext.getString(R.string.notifications_channel_id)
        )

        builder.setSmallIcon(R.drawable.time)
            .setContentTitle(messageTitle)
            .setContentText(messageBody)
            .setContentIntent(
                PendingIntent.getActivity(
                    applicationContext,
                    0,
                    activityIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            )
            .setContentText(messageBody)

        notify(0, builder.build())
    }

}