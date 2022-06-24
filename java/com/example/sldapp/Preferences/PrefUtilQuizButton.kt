package com.example.sldapp.Preferences

import android.content.Context
import androidx.preference.PreferenceManager

class PrefUtilQuizButton {
    companion object {

        private const val QUIZ_BUTTON_STATE_ID = "com.example.sldapp.quiz_button_state"

        fun getQuizButtonState(context: Context): String {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getString(QUIZ_BUTTON_STATE_ID, "")!!
        }

        fun setQuizButtonState(state : String, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit().putString(
                QUIZ_BUTTON_STATE_ID, state)
            editor.apply()
        }
    }
}