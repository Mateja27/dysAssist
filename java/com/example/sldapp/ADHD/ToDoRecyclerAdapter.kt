package com.example.sldapp.ADHD

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sldapp.ADHD.AdhdActivity.Companion.timerState
import com.example.sldapp.Preferences.PrefUtilTimerTextView
import com.example.sldapp.Preferences.PrefUtilTimerTextView.Companion.getClickedTextViewName
import com.example.sldapp.Preferences.PrefUtilTimerTextView.Companion.getIfTextViewClicked
import com.example.sldapp.Preferences.PrefUtilTimerTextView.Companion.setIfTextViewClicked
import com.example.sldapp.R

private var tvList = ArrayList<TextView>()
class ToDoRecyclerAdapter(private var items: List<ToDoList>, private val choiceListener: TimerToListListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adhd_recycler, parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ToDoViewHolder -> {
                holder.bind(items[position], choiceListener)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ToDoViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val taskTitle: TextView =
            itemView.findViewById(R.id.text_adhd_recycler_task)
        private val rating: RatingBar =
            itemView.findViewById(R.id.rating_adhd_recycler)
        private val deleteImageButton = itemView.findViewById<ImageButton>(R.id.image_button_adhd_recycler)
        private val context = taskTitle.context
        fun bind(todos: ToDoList, choiceListener: TimerToListListener) {
            tvList.add(taskTitle)
            taskTitle.text = todos.toDoName
            rating.rating = todos.pomodoroCount.toFloat()
            if(timerState == AdhdActivity.TimerState.Stopped)
                clearBackgrounds()
            else
                setSelectedBackground()

            deleteImageButton.setOnClickListener {
                choiceListener.onDelete(todos.toDoName, false)
            }
            taskTitle.setOnClickListener {
                if(timerState == AdhdActivity.TimerState.Running || timerState == AdhdActivity.TimerState.Paused){
                    Toast.makeText(context, "Obaveza je već odabrana", Toast.LENGTH_SHORT).show()
                }else{
                    setIfTextViewClicked(0,context)
                    clearBackgrounds()
                    choiceListener.onChoose(todos.toDoName, todos.pomodoroCount, taskTitle)
                }
            }
        }

        private fun setSelectedBackground() {
            for (tv in tvList){
                if(tv.text == getClickedTextViewName(context)){
                    tv.background = ContextCompat.getDrawable(context,
                        R.drawable.dyscalculia_border_option_selected
                    )
                }
            }
        }

        private fun clearBackgrounds() {
            for (tv in tvList){
                tv.background = ContextCompat.getDrawable(context,
                    R.drawable.adhd_border_default
                )
            }
        }
    }
}