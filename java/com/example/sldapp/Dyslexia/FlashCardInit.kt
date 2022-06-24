package com.example.sldapp.Dyslexia

import com.example.sldapp.R

object FlashCardInit {
    const val DECK_SIZE : String = "deck_size"
    const val KNOWN_CARDS_COUNT : String = "known_cards"

    fun getFlashCards(): ArrayList<FlashCard>{
        val flashCardList = ArrayList<FlashCard>()

        val firstOnList = FlashCard(
            "kad",
            R.drawable.dyslexia_flashcard_image_ice_cream,
            "Kad",
            "  završiš  zadaću,  jesti  ćemo  sladoled.",
            "",
            R.raw.audio_kad_front,
            R.raw.audio_kad
        )
        flashCardList.add(firstOnList)

        val secondOnList = FlashCard(
            "što",
            R.drawable.dyslexia_flashcard_image_present,
            "Što",
            "  želiš  za  rođendan?",
            "",
            R.raw.audio_sto_front,
            R.raw.audio_sto
        )
        flashCardList.add(secondOnList)

        val thirdOnList = FlashCard(
            "svaki",
            R.drawable.dyslexia_flashcard_image_pool,
            "Kupam  se  u  bazenu  ",
            "svaki",
            "  dan.",
            R.raw.audio_svaki_front,
            R.raw.audio_svaki
        )
        flashCardList.add(thirdOnList)

        val fourthOnList = FlashCard(
            "ali",
            R.drawable.dyslexia_flashcard_image_rain,
            "Htjeli  smo  šetati,  ",
            "ali",
            "  je  počela  padati  kiša.",
            R.raw.audio_ali_front,
            R.raw.audio_ali
        )
        flashCardList.add(fourthOnList)

        val fifthOnList = FlashCard(
            "sve",
            R.drawable.dyslexia_flashcard_image_apples_in_a_bowl,
            "Sve",
            "  jabuke  u  zdjeli  su  crvene.",
            "",
            R.raw.audio_sve_front,
            R.raw.audio_sve
        )
        flashCardList.add(fifthOnList)

        val sixthOnList = FlashCard(
            "često",
            R.drawable.dyslexia_flashcard_image_coffee,
            "Često",
            "  pijem  kavu  na  terasi.",
            "",
            R.raw.audio_cesto_front,
            R.raw.audio_cesto
        )
        flashCardList.add(sixthOnList)

        val seventhOnList = FlashCard(
            "bio",
            R.drawable.dyslexia_flashcard_image_earthquake,
            "To  je  ",
            "bio",
            "  najveći  potres  2021.  godine.",
            R.raw.audio_bio_front,
            R.raw.audio_bio
        )
        flashCardList.add(seventhOnList)

        val eightOnList = FlashCard(
            "skoro",
            R.drawable.dyslexia_flashcard_image_christmas_tree,
            "Veselo  božićno  vrijeme  je  ",
            "skoro",
            "  stiglo!",
            R.raw.audio_skoro_front,
            R.raw.audio_skoro
        )
        flashCardList.add(eightOnList)

        val ninthOnList = FlashCard(
            "oduvijek",
            R.drawable.dyslexia_flashcard_image_beach_house,
           "Oduvijek",
            "  sam  želio  živjeti  u  kući  na  plaži.",
            "",
            R.raw.audio_oduvijek_front,
            R.raw.audio_oduvijek
        )
        flashCardList.add(ninthOnList)

        val tenthOnList = FlashCard(
            "ići",
            R.drawable.dyslexia_flashcard_image_festival,
            "Želiš  li  ",
            "ići",
            "  na  festival?",
            R.raw.audio_ici_front,
            R.raw.audio_ici
        )
        flashCardList.add(tenthOnList)

        val eleventhOnList = FlashCard(
            "ponovo",
            R.drawable.dyslexia_flashcard_image_japan,
            "U  travnju  ",
            "ponovo",
            "  putujem  u  Japan.",
            R.raw.audio_ponovo_front,
            R.raw.audio_ponovo
        )
        flashCardList.add(eleventhOnList)

        val twelfthOnList = FlashCard(
            "između",
            R.drawable.dyslexia_flashcard_image_dog_and_cats,
            "Pas  sjedi  ",
            "između",
            "  dvije  mačke.",
            R.raw.audio_izmedu_front,
            R.raw.audio_izmedu
        )
        flashCardList.add(twelfthOnList)

        return flashCardList
    }
}