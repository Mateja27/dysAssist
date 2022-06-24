package com.example.sldapp.Preferences

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.sldapp.ADHD.AdhdActivity
import com.example.sldapp.R

class PrefUtilNotification {
    companion object{
        fun sendNotification(context: Context){
            val intent = Intent(context, AdhdActivity::class.java).apply{
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent : PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            val builder = NotificationCompat.Builder(context, PrefUtilTimer.CHANNEL_ID)
                .setSmallIcon(R.drawable.adhd_rating_bar_pomodoro_fill)
                .setContentTitle("Čestitamo! Uspješno odrađen Pomodoro!")
                .setContentText("Kliknite za otvaranje prozora odbrojavanja.")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            with(NotificationManagerCompat.from(context)) {
                notify(PrefUtilTimer.NOTIFICATION_ID, builder.build() )
            }
        }
    }
}