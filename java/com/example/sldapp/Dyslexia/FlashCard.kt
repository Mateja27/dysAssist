package com.example.sldapp.Dyslexia

data class FlashCard(
    val frontWord : String,
    val backImage : Int,
    val backSentenceFP : String,
    val backSentenceSP: String,
    val backSentenceTP: String,
    val ttsWord : Int,
    val ttsSentence : Int
)
