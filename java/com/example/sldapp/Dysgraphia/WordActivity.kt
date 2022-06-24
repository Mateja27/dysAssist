package com.example.sldapp.Dysgraphia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.sldapp.Preferences.PrefUtilLetter
import com.example.sldapp.Preferences.PrefUtilWord
import com.example.sldapp.R

class WordActivity : AppCompatActivity() {
    private var isFirst = false
    private var isSecond = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word)

        val kaoWord = findViewById<TextView>(R.id.text_word_kao)
        val jaWord = findViewById<TextView>(R.id.text_word_ja)
        val njegovWord = findViewById<TextView>(R.id.text_word_njegov)
        val daWord = findViewById<TextView>(R.id.text_word_da)
        val onWord = findViewById<TextView>(R.id.text_word_on)
        val bioWord = findViewById<TextView>(R.id.text_word_bio)
        val premaWord = findViewById<TextView>(R.id.text_word_prema)
        val naWord = findViewById<TextView>(R.id.text_word_na)
        val suWord = findViewById<TextView>(R.id.text_word_su)
        val bitiWord = findViewById<TextView>(R.id.text_word_biti)
        val viseWord = findViewById<TextView>(R.id.text_word_vise)
        val saWord =  findViewById<TextView>(R.id.text_word_sa)
        val zaWord = findViewById<TextView>(R.id.text_word_za)
        val ovdjeWord = findViewById<TextView>(R.id.text_word_ovdje)
        val jedanWord = findViewById<TextView>(R.id.text_word_jedan)
        val izWord = findViewById<TextView>(R.id.text_word_iz)
        val oniWord = findViewById<TextView>(R.id.text_word_oni)
        val ondaWord = findViewById<TextView>(R.id.text_word_onda)
        val kakoWord = findViewById<TextView>(R.id.text_word_kako)
        val bezWord = findViewById<TextView>(R.id.text_word_bez)
        val stoWord = findViewById<TextView>(R.id.text_word_sto)
        val opetWord = findViewById<TextView>(R.id.text_word_opet)
        val zastoWord = findViewById<TextView>(R.id.text_word_zasto)
        val ovoWord = findViewById<TextView>(R.id.text_word_ovo)


        kaoWord.setOnClickListener {
            positionChecker(kaoWord.text.toString())
        }
        jaWord.setOnClickListener {
            positionChecker(jaWord.text.toString())
        }
         njegovWord.setOnClickListener {
            positionChecker(njegovWord.text.toString())
        }
        daWord.setOnClickListener {
            positionChecker(daWord.text.toString())
        }
        onWord.setOnClickListener {
            positionChecker(onWord.text.toString())
        }
        bioWord.setOnClickListener {
            positionChecker(bioWord.text.toString())
        }
        premaWord.setOnClickListener {
            positionChecker(premaWord.text.toString())
        }
        naWord.setOnClickListener {
            positionChecker(naWord.text.toString())
        }
        suWord.setOnClickListener {
            positionChecker(suWord.text.toString())
        }
        bitiWord.setOnClickListener {
            positionChecker(bitiWord.text.toString())
        }
        viseWord.setOnClickListener {
            positionChecker(viseWord.text.toString())
        }
        saWord.setOnClickListener {
            positionChecker(saWord.text.toString())
        }
        zaWord.setOnClickListener {
            positionChecker(zaWord.text.toString())
        }
        ovdjeWord.setOnClickListener {
            positionChecker(ovdjeWord.text.toString())
        }
        jedanWord.setOnClickListener {
            positionChecker(jedanWord.text.toString())
        }
        izWord.setOnClickListener {
            positionChecker(izWord.text.toString())
        }
        oniWord.setOnClickListener {
            positionChecker(oniWord.text.toString())
        }
        ondaWord.setOnClickListener {
            positionChecker(ondaWord.text.toString())
        }
        kakoWord.setOnClickListener {
            positionChecker(kakoWord.text.toString())
        }
        bezWord.setOnClickListener {
            positionChecker(bezWord.text.toString())
        }
        stoWord.setOnClickListener {
            positionChecker(stoWord.text.toString())
        }
        opetWord.setOnClickListener {
            positionChecker(opetWord.text.toString())
        }
        zastoWord.setOnClickListener {
            positionChecker(zastoWord.text.toString())
        }
        ovoWord.setOnClickListener {
            positionChecker(ovoWord.text.toString())
        }

        findViewById<Button>(R.id.button_word_back).setOnClickListener {
            startDysgraphiaActivity()
        }
    }

    private fun startDysgraphiaActivity() {
        intent = Intent(this, DysgraphiaActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0,0)
        finish()
    }

    private fun positionChecker(text : String){
        //postavljanje u preference koje su dvije riječi izabrane za prikaz i skrivanje slova
        if (!isFirst && !isSecond)
        {
            isFirst = true
            PrefUtilWord.setFirstClicked(text, applicationContext)
        }else if(isFirst && !isSecond) {
            isSecond = true
            PrefUtilLetter.setHideLetter(true, applicationContext)
            startCanvasActivity()
            PrefUtilWord.setSecondClicked(text, applicationContext)
        }
    }

    private fun startCanvasActivity() {
        intent = Intent(this, CanvasActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0, 0)
        finish()
    }
}