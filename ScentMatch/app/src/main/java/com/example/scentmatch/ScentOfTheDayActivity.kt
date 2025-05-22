package com.example.scentmatch

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class ScentOfTheDayActivity : AppCompatActivity() {

    private val scents = listOf(
        "🌸 Rose – A classic floral scent symbolizing love and elegance.",
        "🍋 Citrus – Energizing and fresh, perfect for summer vibes.",
        "🌲 Sandalwood – Warm and woody, often used in unisex fragrances.",
        "🌿 Lavender – Soothing and calming, great for stress relief.",
        "🔥 Amber – Rich and warm with a slightly sweet edge.",
        "🍎 Fruity – Fun and playful, often youthful and sweet.",
        "🌾 Vetiver – Earthy and smoky, used in many masculine perfumes."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scent_of_the_day)

        val scentText = findViewById<TextView>(R.id.scent_text)
        val btnNewScent = findViewById<Button>(R.id.btn_new_scent)
        val btnClose = findViewById<Button>(R.id.btn_close)

        fun showRandomScent() {
            val randomScent = scents[Random.nextInt(scents.size)]
            scentText.text = randomScent
        }

        showRandomScent()

        btnNewScent.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
            scentText.startAnimation(animation)
            showRandomScent()
        }

        btnClose.setOnClickListener {
            finish()
        }
    }
}
