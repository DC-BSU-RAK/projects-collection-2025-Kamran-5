package com.example.scentmatch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val startQuiz = findViewById<CardView>(R.id.card_start_quiz)
        val preferences = findViewById<CardView>(R.id.card_preferences)
        val instructions = findViewById<CardView>(R.id.card_instructions)
        val scentOfDay = findViewById<CardView>(R.id.card_scent_day)

        startQuiz.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }

        preferences.setOnClickListener {
            startActivity(Intent(this, PreferencesActivity::class.java))
        }

        instructions.setOnClickListener {
            startActivity(Intent(this, InstructionsActivity::class.java))
        }

        scentOfDay.setOnClickListener {
            startActivity(Intent(this, ScentOfTheDayActivity::class.java))
        }
    }
}
