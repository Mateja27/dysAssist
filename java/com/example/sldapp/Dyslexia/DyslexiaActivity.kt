package com.example.sldapp.Dyslexia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import com.example.sldapp.Dyslexia.FlashCardInit.DECK_SIZE
import com.example.sldapp.MainActivity
import com.example.sldapp.R

class DyslexiaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dyslexia)


        findViewById<Button>(R.id.button_dyslexia_begin).setOnClickListener {
            val fifteenCardsRB = findViewById<RadioButton>(R.id.radio_button_cards_fifteen)
            val twentyCardsRB = findViewById<RadioButton>(R.id.radio_button_cards_twenty)
            val tenCardsRB = findViewById<RadioButton>(R.id.radio_button_cards_ten)
            val intent = Intent(this, FlashcardActivity::class.java)
            when {
                twentyCardsRB.isChecked -> {
                    intent.putExtra(DECK_SIZE, 20)
                }
                fifteenCardsRB.isChecked -> {
                    intent.putExtra(DECK_SIZE, 15)
                }
                tenCardsRB.isChecked -> {
                    intent.putExtra(DECK_SIZE, 10)
                }
            }
            startActivity(intent)
            overridePendingTransition(0,0)
            finish()
        }

        findViewById<Button>(R.id.button_dyslexia_back).setOnClickListener {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0,0)
        finish()
    }
}