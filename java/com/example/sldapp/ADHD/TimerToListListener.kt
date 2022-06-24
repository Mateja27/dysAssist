package com.example.sldapp.ADHD

import android.widget.TextView

interface TimerToListListener {
    fun onChoose(toDoName : String, pomodoroCount : String, textView : TextView)
    fun onDelete(toDoName: String, checker : Boolean)
}