package com.example.sldapp.Dyscalculia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import com.example.sldapp.Dyscalculia.QuestionGenerator.QUESTION_NUMBER
import com.example.sldapp.Dyslexia.FlashCardInit
import com.example.sldapp.MainActivity
import com.example.sldapp.R

class DyscalculiaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dyscalculia)

        findViewById<Button>(R.id.button_dyscalculia_back).setOnClickListener {
            startMainActivity()
        }

        findViewById<Button>(R.id.button_dyscalculia_begin).setOnClickListener {
            val fifteenCardsRB = findViewById<RadioButton>(R.id.radio_button_dyscalculia_fifteen)
            val twentyCardsRB = findViewById<RadioButton>(R.id.radio_button_dyscalculia_twenty)
            val tenCardsRB = findViewById<RadioButton>(R.id.radio_button_dyscalculia_ten)
            val intent = Intent(this, QuizQuestionsActivity::class.java)
            when {
                twentyCardsRB.isChecked -> {
                    intent.putExtra(QUESTION_NUMBER, 20)
                }
                fifteenCardsRB.isChecked -> {
                    intent.putExtra(QUESTION_NUMBER, 15)
                }
                tenCardsRB.isChecked -> {
                    intent.putExtra(QUESTION_NUMBER, 10)
                }
            }
            startActivity(intent)
            overridePendingTransition(0,0)
            finish()
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0,0)
        finish()
    }
}