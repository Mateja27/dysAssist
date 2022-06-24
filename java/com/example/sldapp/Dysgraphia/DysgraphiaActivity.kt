package com.example.sldapp.Dysgraphia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.sldapp.MainActivity
import com.example.sldapp.R

class DysgraphiaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dysgraphia)

        findViewById<Button>(R.id.button_dysgraphia_choose_letter).setOnClickListener {
            startLetterActivity()
        }

        findViewById<Button>(R.id.button_dysgraphia_choose_word).setOnClickListener {
            startWordActivity()
        }

        findViewById<Button>(R.id.button_dysgraphia_home).setOnClickListener {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0,0)
        finish()
    }

    private fun startWordActivity() {
        intent = Intent(this, WordActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0,0)
        finish()
    }

    private fun startLetterActivity() {
        intent = Intent(this, LetterActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0,0)
        finish()
    }
}