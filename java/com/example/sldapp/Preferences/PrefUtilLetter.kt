package com.example.sldapp.Preferences

import android.content.Context
import androidx.preference.PreferenceManager

class PrefUtilLetter {
    companion object{

        const val LETTER_SENT : String = "sent_letter"
        private const val HIDE_LETTER_ID = "com.example.sideapp_hide_letter"

        fun getHideLetter(context: Context) : Boolean {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getBoolean(HIDE_LETTER_ID, false)
        }

        fun setHideLetter(text : Boolean, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(
                HIDE_LETTER_ID, text)
            editor.apply()
        }
    }
}