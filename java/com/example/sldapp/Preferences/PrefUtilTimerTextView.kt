package com.example.sldapp.Preferences

import android.content.Context
import androidx.preference.PreferenceManager

class PrefUtilTimerTextView {
    companion object{
        private const val TEXT_VIEW_CLICKED_ID = "com.example.sideapp_text_view_clicked"

        fun getIfTextViewClicked(context: Context) : Int {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getInt(TEXT_VIEW_CLICKED_ID, 0)
        }

        fun setIfTextViewClicked(clicked : Int, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(
                TEXT_VIEW_CLICKED_ID, clicked)
            editor.apply()

        }

        private const val POSITION_CLICKED_ID = "com.example.sideapp_position_clicked"

        fun getClickedTextViewName(context: Context) : String {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getString(POSITION_CLICKED_ID, "")!!
        }

        fun setClickedTextViewName(position : String, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit().putString(
                POSITION_CLICKED_ID, position)
            editor.apply()

        }
    }
}