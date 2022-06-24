package com.example.sldapp.Dyscalculia

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.sldapp.Dyscalculia.QuestionGenerator.QUESTION_NUMBER
import com.example.sldapp.Dyscalculia.QuestionGenerator.QUIZ_RESULT
import com.example.sldapp.Preferences.PrefUtilQuizButton
import com.example.sldapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class QuizQuestionsActivity : AppCompatActivity() , View.OnClickListener {

    private  var questionNumber : Int = 10
    private var mCurrentPosition: Int = 1
    private var mQuestionList : ArrayList<Question>? = null
    private var mSelectedOption : Int = 0
    private var mCorrectAnswers : Int = 0

    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var database : DatabaseReference
    private lateinit var quizQuestion : TextView
    private lateinit var firstAnswer : TextView
    private lateinit var secondAnswer : TextView
    private lateinit var thirdAnswer : TextView
    private lateinit var leftApple : ImageView
    private lateinit var rightApple : ImageView
    private lateinit var submitButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var progress : TextView
    private lateinit var operatorTV : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)


        //preko uid se povezujemo s bazom podataka
        fireBaseInit()
        //inicijalizacija viewova
        viewInit()
        questionNumber = intent.getIntExtra(QUESTION_NUMBER, 0)
        mQuestionList = QuestionGenerator.getQuestions(questionNumber)
        setQuestion()
        firstAnswer.setOnClickListener(this)
        secondAnswer.setOnClickListener(this)
        thirdAnswer.setOnClickListener(this)
        submitButton.setOnClickListener(this)
    }
    private fun setQuestion(){
        PrefUtilQuizButton.setQuizButtonState("next", applicationContext)
        val question = mQuestionList!![mCurrentPosition-1]
        defaultOptionsView()

        //promjena teksta na buttonu
        if(mCurrentPosition == mQuestionList!!.size) {
            endOrConfirmBtnText()
        } else {
            nextOrConfirmBtnText()
        }
        //povezivanje Viewova s objektima podatkovne klase Question koje dobivamo u listi iz QuestionGeneratora
        progressBar.progress = mCurrentPosition
        progressBar.max = questionNumber
        progress.text = "$mCurrentPosition" + "/" + progressBar.max
        quizQuestion.text = question.question
        operatorTV.text = setOperatorDisplay()
        firstAnswer.text = question.optionOne
        secondAnswer.text = question.optionTwo
        thirdAnswer.text = question.optionThree
        leftApple.setImageResource(question.leftApple)
        rightApple.setImageResource(question.rightApple)
    }

    private fun nextOrConfirmBtnText() {
        if(PrefUtilQuizButton.getQuizButtonState(applicationContext) == "selected")
            submitButton.text= "DALJE"
        else
            submitButton.text = "POTVRDI"
    }

    private fun endOrConfirmBtnText() {
        if(PrefUtilQuizButton.getQuizButtonState(applicationContext) == "selected")
            submitButton.text= "ZAVRŠI"
        else
            submitButton.text = "POTVRDI"
    }


    private fun defaultOptionsView(){
        //na sve odgovore se stavlja default pozadina i boja na početku
           val options = ArrayList<TextView>()
            options.add(0,firstAnswer)
            options.add(1,secondAnswer)
            options.add(2,thirdAnswer)

        for (option in options){
                option.setTextColor(Color.parseColor("#7A8089"))
                option.typeface = Typeface.DEFAULT
                option.background = ContextCompat.getDrawable(this,
                    R.drawable.dyscalculia_border_option_default
                )
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            //poziva se selectedOptionView na klik miša
            R.id.text_quiz_question_answer_first -> {
                if(PrefUtilQuizButton.getQuizButtonState(applicationContext) == "selected"){
                    Toast.makeText(applicationContext, "Odgovor je već odabran.", Toast.LENGTH_SHORT).show()
                }
                else
                  selectedOptionView(firstAnswer, 1)
            }
            R.id.text_quiz_question_answer_second -> {
                if(PrefUtilQuizButton.getQuizButtonState(applicationContext) == "selected"){
                    Toast.makeText(applicationContext, "Odgovor je već odabran.", Toast.LENGTH_SHORT).show()
                }
                else
                selectedOptionView(secondAnswer, 2)
            }
            R.id.text_quiz_question_answer_third -> {
                if(PrefUtilQuizButton.getQuizButtonState(applicationContext) == "selected"){
                    Toast.makeText(applicationContext, "Odgovor je već odabran.", Toast.LENGTH_SHORT).show()
                }
                else
                selectedOptionView(thirdAnswer, 3)
            }
            R.id.button_quiz_question_submit -> {
                if(mSelectedOption == 0){
                    if(PrefUtilQuizButton.getQuizButtonState(applicationContext) == "next"){
                        Toast.makeText(applicationContext, "Odaberite odgovor.", Toast.LENGTH_SHORT).show()
                    }else{
                        mCurrentPosition++
                        when{
                            mCurrentPosition <= mQuestionList!!.size -> {
                                setQuestion()
                            }
                            else -> {
                                //ako smo na kraju liste pitanja
                                val result = (mCorrectAnswers.toFloat() / questionNumber!!.toFloat()) * 100
                                databaseAddScore(result)
                                startQuizResultActivity(result)
                            }
                        }
                    }
                } else {
                    //ako je označen odgovor
                    PrefUtilQuizButton.setQuizButtonState("selected", applicationContext)
                    val question = mQuestionList?.get(mCurrentPosition-1)
                    if(question!!.correctOption != mSelectedOption){
                        //ako je odgovor netočan
                        answerView(mSelectedOption, R.drawable.dyscalculia_border_option_wrong)
                        answerSoundPlayer(R.raw.audio_wrong_answer1)
                    }else{
                        //ako je odgovor točan
                        mCorrectAnswers++
                        answerSoundPlayer(R.raw.audio_clapping_shorter)
                    }
                    answerView(question.correctOption, R.drawable.dyscalculia_border_option_correct)

                    //postavljanje teksta na buttonu
                    if(mCurrentPosition == mQuestionList!!.size){
                        endOrConfirmBtnText()
                    }
                    else{
                        nextOrConfirmBtnText()
                    }
                    mSelectedOption = 0
                }
            }
        }
    }

    private fun startQuizResultActivity(result : Float) {
        val intent = Intent(this, QuizResultActivity::class.java)
        intent.putExtra(QUIZ_RESULT, result.toInt())
        startActivity(intent)
        overridePendingTransition(0,0)
        finish()
    }

    private fun databaseAddScore(result: Float) {
        val quizID = database.push().key
        if (quizID != null) {
            database.child(quizID).child("score").setValue(result.toInt())
        }
    }

    private fun answerSoundPlayer(audio: Int) {
        val mediaPlayerWrong = MediaPlayer.create(this, audio)
        mediaPlayerWrong.start()
    }

    private fun answerView(answer : Int, drawableView : Int){
        //funkvija za povezivanje odgovora s crvenom i zelenom pozadinom
        when (answer){
            1 -> {
                firstAnswer.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                secondAnswer.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                thirdAnswer.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    private fun setOperatorDisplay() : String{
        //dobivanje operatora za textView između slika jabuka
        return if(quizQuestion.text.contains("+")) {
            "+"
        }else if(quizQuestion.text.contains("-")){
            "-"
        }else{
            "*"
        }
    }

    private fun selectedOptionView(tv : TextView, selectedOptionNum : Int){
        defaultOptionsView()
        mSelectedOption = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.dyscalculia_border_option_selected)
    }

    private fun viewInit(){
        quizQuestion = findViewById(R.id.text_quiz_question)
        firstAnswer = findViewById(R.id.text_quiz_question_answer_first)
        secondAnswer = findViewById(R.id.text_quiz_question_answer_second)
        thirdAnswer = findViewById(R.id.text_quiz_question_answer_third)
        leftApple = findViewById(R.id.image_quiz_question_left)
        rightApple = findViewById(R.id.image_quiz_question_right)
        progressBar = findViewById(R.id.progress_quiz_question)
        progress = findViewById(R.id.text_quiz_question_progress)
        submitButton = findViewById(R.id.button_quiz_question_submit)
        operatorTV = findViewById(R.id.text_quiz_question_oprator)
    }

    private fun fireBaseInit(){
        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            val user = firebaseAuth.currentUser!!.uid
            database = FirebaseDatabase.getInstance("https://sld-project-default-rtdb.europe-west1.firebasedatabase.app//").getReference("User").child(user).child("Dyscalculia")
        }
    }
}