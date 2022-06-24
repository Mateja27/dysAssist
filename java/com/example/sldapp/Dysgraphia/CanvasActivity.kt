package com.example.sldapp.Dysgraphia

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.sldapp.Preferences.PrefUtilLetter
import com.example.sldapp.Preferences.PrefUtilLetter.Companion.LETTER_SENT
import com.example.sldapp.Preferences.PrefUtilWord
import com.example.sldapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class CanvasActivity : AppCompatActivity() {

    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var database : DatabaseReference

    lateinit var letterShown : TextView
    lateinit var firstWordShown : TextView
    lateinit var secondWordShown : TextView
    private var databaseCheck : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas)

        val paintView = findViewById<PaintView>(R.id.paint_view)
        fireBaseInit()
        hideLetterOrWords()

        findViewById<Button>(R.id.button_canvas_back).setOnClickListener {
            databaseCheck = true
            databaseRemoveAll()
            startDysgraphiaActivity()
        }
        findViewById<Button>(R.id.button_canvas_delete_all).setOnClickListener{
            databaseCheck = true
            paintView.onReset()
            databaseRemoveAll()
        }
        findViewById<Button>(R.id.button_canvas_delete).setOnClickListener{
           paintView.onUndo()
        }
    }

    private fun startDysgraphiaActivity() {
        intent = Intent(this, DysgraphiaActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0,0)
        finish()
    }

    private fun databaseRemoveAll() {
        database.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot){
                if(databaseCheck){
                    for(pathSnapshot in p0.children) {
                        pathSnapshot.ref.removeValue()
                    }
                    databaseCheck = false
                }
            }
            override fun onCancelled(p0: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.")
            }
        })
    }

    private fun hideLetterOrWords(){
        letterShown = findViewById(R.id.text_paint_letter)
        firstWordShown = findViewById(R.id.text_paint_word_first)
        secondWordShown = findViewById(R.id.text_paint_word_second)

        if(PrefUtilLetter.getHideLetter(applicationContext)){
            letterShown.visibility = View.GONE

            firstWordShown.text = PrefUtilWord.getFirstClicked(applicationContext)
            secondWordShown.text = PrefUtilWord.getSecondClicked(applicationContext)
            checkWordsSize()
            firstWordShown.visibility = View.VISIBLE
            secondWordShown.visibility = View.VISIBLE
        }else{
            letterShown.visibility = View.VISIBLE
            firstWordShown.visibility = View.GONE
            secondWordShown.visibility = View.GONE
        }
        if(intent.getStringExtra(LETTER_SENT) != null){
            checkLettersSize()
            letterShown.text = intent.getStringExtra(LETTER_SENT)
        }
    }

    private fun checkLettersSize() {
        if( intent.getStringExtra(LETTER_SENT) == "Dž dž"){
            letterShown.textSize = 180F
        }
        if(intent.getStringExtra(LETTER_SENT) == "M m" || intent.getStringExtra(LETTER_SENT) == "Nj nj"){
            letterShown.textSize = 200F
        }
    }

    private fun checkWordsSize() {
        if(PrefUtilWord.getFirstClicked(applicationContext) == "njegov" || PrefUtilWord.getFirstClicked(applicationContext) == "zašto" || PrefUtilWord.getFirstClicked(applicationContext) == "prema"|| PrefUtilWord.getFirstClicked(applicationContext) == "jedan"){
            firstWordShown.textSize = 120F
        }
        if(PrefUtilWord.getSecondClicked(applicationContext) == "njegov" ||PrefUtilWord.getSecondClicked(applicationContext) == "zašto" || PrefUtilWord.getSecondClicked(applicationContext) == "prema"|| PrefUtilWord.getSecondClicked(applicationContext) == "jedan") {
            secondWordShown.textSize = 120F
        }
    }

    private fun fireBaseInit(){
        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            val user = firebaseAuth.currentUser!!.uid
            database = FirebaseDatabase.getInstance("https://sld-project-default-rtdb.europe-west1.firebasedatabase.app//").getReference("User").child(user).child("Dysgraphia")
        }
    }
}