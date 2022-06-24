package com.example.sldapp.Dysgraphia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.sldapp.Preferences.PrefUtilLetter
import com.example.sldapp.Preferences.PrefUtilLetter.Companion.LETTER_SENT
import com.example.sldapp.R


class LetterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letter)

        val letterA = findViewById<TextView>(R.id.text_letter_A)
        val letterB = findViewById<TextView>(R.id.text_letter_B)
        val letterC = findViewById<TextView>(R.id.text_letter_C)
        val letterCC = findViewById<TextView>(R.id.text_letter_CC)
        val letterCCC = findViewById<TextView>(R.id.text_letter_CCC)
        val letterD = findViewById<TextView>(R.id.text_letter_D)
        val letterDD = findViewById<TextView>(R.id.text_letter_DD)
        val letterDDD = findViewById<TextView>(R.id.text_letter_DDD)
        val letterE = findViewById<TextView>(R.id.text_letter_E)
        val letterF = findViewById<TextView>(R.id.text_letter_F)
        val letterG = findViewById<TextView>(R.id.text_letter_G)
        val letterH = findViewById<TextView>(R.id.text_letter_H)
        val letterI = findViewById<TextView>(R.id.text_letter_I)
        val letterJ = findViewById<TextView>(R.id.text_letter_J)
        val letterK = findViewById<TextView>(R.id.text_letter_K)
        val letterL = findViewById<TextView>(R.id.text_letter_L)
        val letterLJ = findViewById<TextView>(R.id.text_letter_LJ)
        val letterM = findViewById<TextView>(R.id.text_letter_M)
        val letterN = findViewById<TextView>(R.id.text_letter_N)
        val letterNJ = findViewById<TextView>(R.id.text_letter_NJ)
        val letterO = findViewById<TextView>(R.id.text_letter_O)
        val letterP = findViewById<TextView>(R.id.text_letter_P)
        val letterR = findViewById<TextView>(R.id.text_letter_R)
        val letterS = findViewById<TextView>(R.id.text_letter_S)
        val letterSS = findViewById<TextView>(R.id.text_letter_SS)
        val letterT = findViewById<TextView>(R.id.text_letter_T)
        val letterU = findViewById<TextView>(R.id.text_letter_U)
        val letterV = findViewById<TextView>(R.id.text_letter_V)
        val letterZ = findViewById<TextView>(R.id.text_letter_Z)
        val letterZZ = findViewById<TextView>(R.id.text_letter_ZZ)


        letterA.setOnClickListener {
            sendLetter(letterA.text.toString())
        }
        letterB.setOnClickListener {
            sendLetter(letterB.text.toString())
        }
        letterC.setOnClickListener {
            sendLetter(letterC.text.toString())
        }
        letterCC.setOnClickListener {
            sendLetter(letterCC.text.toString())
        }
        letterCCC.setOnClickListener {
            sendLetter(letterCCC.text.toString())
        }
        letterD.setOnClickListener {
            sendLetter(letterD.text.toString())
        }
        letterDD.setOnClickListener {
            sendLetter(letterDD.text.toString())
        }
        letterDDD.setOnClickListener {
            sendLetter(letterDDD.text.toString())
        }
        letterE.setOnClickListener {
            sendLetter(letterE.text.toString())
        }
        letterF.setOnClickListener {
            sendLetter(letterF.text.toString())
        }
        letterG.setOnClickListener {
            sendLetter(letterG.text.toString())
        }
        letterH.setOnClickListener {
            sendLetter(letterH.text.toString())
        }
        letterI.setOnClickListener {
            sendLetter(letterI.text.toString())
        }
        letterJ.setOnClickListener {
            sendLetter(letterJ.text.toString())
        }
        letterK.setOnClickListener {
            sendLetter(letterK.text.toString())
        }
        letterL.setOnClickListener {
            sendLetter(letterL.text.toString())
        }
        letterLJ.setOnClickListener {
            sendLetter(letterLJ.text.toString())
        }
        letterM.setOnClickListener {
            sendLetter(letterM.text.toString())
        }
        letterN.setOnClickListener {
            sendLetter(letterN.text.toString())
        }
        letterNJ.setOnClickListener {
            sendLetter(letterNJ.text.toString())
        }
        letterO.setOnClickListener {
            sendLetter(letterO.text.toString())
        }
        letterP.setOnClickListener {
            sendLetter(letterP.text.toString())
        }
        letterR.setOnClickListener {
            sendLetter(letterR.text.toString())
        }
        letterS.setOnClickListener {
            sendLetter(letterS.text.toString())
        }
        letterSS.setOnClickListener {
            sendLetter(letterSS.text.toString())
        }
        letterT.setOnClickListener {
            sendLetter(letterT.text.toString())
        }
        letterU.setOnClickListener {
            sendLetter(letterU.text.toString())
        }
        letterV.setOnClickListener {
            sendLetter(letterV.text.toString())
        }
        letterZ.setOnClickListener {
            sendLetter(letterZ.text.toString())
        }
        letterZZ.setOnClickListener {
            sendLetter(letterZZ.text.toString())
        }

        findViewById<Button>(R.id.button_letter_back).setOnClickListener {
            intent = Intent(this, DysgraphiaActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0,0)
            finish()
        }
    }

    private fun sendLetter(letter : String){
        intent = Intent(this, CanvasActivity::class.java)
        intent.putExtra(LETTER_SENT, letter)
        PrefUtilLetter.setHideLetter(false, applicationContext)
        startActivity(intent)
        overridePendingTransition(0,0)
        finish()
    }
}