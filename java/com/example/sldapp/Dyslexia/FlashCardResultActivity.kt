package com.example.sldapp.Dyslexia

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.sldapp.Dyslexia.FlashCardInit.KNOWN_CARDS_COUNT
import com.example.sldapp.MainActivity
import com.example.sldapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class FlashCardResultActivity : AppCompatActivity() {

    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var database : DatabaseReference
    private var scoreList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard_result)

        fireBaseInit()
        showCurrentScore()
        databaseAddScore()

        findViewById<Button>(R.id.button_flashcard_result_back).setOnClickListener{
            val intent = Intent(this, DyslexiaActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0,0)
            finish()
        }
        findViewById<Button>(R.id.button_flashcard_result_home_page).setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0,0)
            finish()
        }
    }

    private fun showCurrentScore() {
        val currentScore = findViewById<TextView>(R.id.text_flashcard_result_score_percent)
        val result = intent.getIntExtra(KNOWN_CARDS_COUNT, 0)
        currentScore.text = " $result%"
    }

    private fun databaseAddScore() {
        database.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()) {
                    for (score in p0.children) {
                        scoreList.add(score.child("score").value.toString().toInt())
                    }
                    showMaxScore()
                }
            }
            override fun onCancelled(p0: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.")
            }
        })
    }

    private fun showMaxScore() {
        val bestScore = findViewById<TextView>(R.id.text_flashcard_result_best_score_percent)
        val max = scoreList.maxOrNull() ?: 0
        bestScore.text = "$max%"
    }

    private fun fireBaseInit(){
        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            val user = firebaseAuth.currentUser!!.uid
            database = FirebaseDatabase.getInstance("https://sld-project-default-rtdb.europe-west1.firebasedatabase.app//").getReference("User").child(user).child("Dyslexia")
        }
    }
}