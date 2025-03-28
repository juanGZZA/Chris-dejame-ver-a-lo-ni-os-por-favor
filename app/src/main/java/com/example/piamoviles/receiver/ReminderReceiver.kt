package com.example.piamoviles.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.piamoviles.notification.NotificationHelper

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notificationHelper = NotificationHelper(context)
        val title = intent.getStringExtra("title") ?: "Recordatorio"
        val message = intent.getStringExtra("message") ?: "Tienes una cita programada"

        notificationHelper.showNotification(title, message)
    }
}