package com.example.sldapp.ADHD

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.sldapp.Preferences.PrefUtilBroadcast
import com.example.sldapp.Preferences.PrefUtilTimer
import com.example.sldapp.Preferences.PrefUtilNotification

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        PrefUtilBroadcast.setBroadcastCompleted(true, context)
        PrefUtilNotification.sendNotification(context)
        PrefUtilTimer.setTimerState(AdhdActivity.TimerState.Stopped, context)
        PrefUtilTimer.setAlarmSetTime(0, context)
    }
}