package com.example.sldapp.Preferences

import android.content.Context
import androidx.preference.PreferenceManager

class PrefUtilBroadcast {
    companion object{

        private const val BROADCAST_ID = "com.example.sideapp_broadcast_completed"

        fun getBroadcastCompleted(context: Context) : Boolean {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getBoolean(BROADCAST_ID, false)
        }

        fun setBroadcastCompleted(state : Boolean, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(
                BROADCAST_ID, state)
            editor.apply()
        }
    }
}