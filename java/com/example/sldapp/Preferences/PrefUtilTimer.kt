package com.example.sldapp.Preferences

import android.content.Context
import androidx.preference.PreferenceManager
import com.example.sldapp.ADHD.AdhdActivity


class PrefUtilTimer {
    companion object{

         const val CHANNEL_ID = "channel_id"
         const val NOTIFICATION_ID = 101
         private const val TIMER_STATE_ID = "com.example.sldapp.timer_state"

        fun getTimerState(context: Context) : AdhdActivity.TimerState{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val ordinal = preferences.getInt(TIMER_STATE_ID, 0)
            return AdhdActivity.TimerState.values()[ordinal]
        }

        fun setTimerState(state : AdhdActivity.TimerState, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            val ordinal = state.ordinal
            editor.putInt(TIMER_STATE_ID, ordinal)
            editor.apply()
        }

        private const val SECONDS_REMAINING_ID = "com.example.sldapp.seconds_remaining"

        fun getSecondsRemaining(context: Context) : Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(SECONDS_REMAINING_ID, 0)
        }

        fun setSecondsRemaining(seconds : Long, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(
                SECONDS_REMAINING_ID, seconds)
            editor.apply()
        }

        private const val ALARM_SET_TIME_ID = "com.example.sideapp.alarm_set_time"

        fun getAlarmSetTime(context: Context) : Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(ALARM_SET_TIME_ID, 0)
        }

        fun setAlarmSetTime(time : Long, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(ALARM_SET_TIME_ID, time)
            editor.apply()
        }
    }
}