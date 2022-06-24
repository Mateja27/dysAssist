package com.example.sldapp.ADHD

data class ToDoList(val toDoName : String, val pomodoroCount : String ){
    constructor() : this("", "" ) {
    }
}

