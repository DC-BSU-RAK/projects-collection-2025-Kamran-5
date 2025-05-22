package com.example.scentmatch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

class QuizResultActivity : AppCompatActivity() {

    private lateinit var scentTypeText: TextView
    private lateinit var perfumeNameText: TextView
    private lateinit var perfumeDescText: TextView
    private lateinit var perfumeImage: ImageView
    private lateinit var rootLayout: LinearLayout
    private lateinit var resultCard: CardView

    private val perfumeImages = mapOf(
        "dior j'adore" to R.drawable.dior_jadore, // Key uses "j'adore" - This will now match!
        "chanel bleu de chanel" to R.drawable.chanel_bleu,
        "versace dylan blue" to R.drawable.versace_dylan_blue,
        "tom ford oud wood" to R.drawable.perfume3,
        "default" to R.drawable.default_perfume
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_result)

        scentTypeText = findViewById(R.id.scentType)
        perfumeNameText = findViewById(R.id.perfumeName)
        perfumeDescText = findViewById(R.id.perfumeDesc)
        perfumeImage = findViewById(R.id.perfumeImage)
        rootLayout = findViewById(R.id.resultRootLayout)
        resultCard = findViewById(R.id.resultCard)

        val scentType = intent.getStringExtra("SCENT_TYPE") ?: "Unknown"
        val perfumeName = intent.getStringExtra("PERFUME_NAME") ?: "Perfume"
        val perfumeDesc = intent.getStringExtra("PERFUME_DESC") ?: "Description"

        scentTypeText.text = when (scentType.lowercase()) {
            "woody" -> "You're Woody! Embrace the rich, earthy notes that define you."
            "floral" -> "You're Floral! Elegant and classic – a blossoming scent awaits."
            "fresh" -> "You're Fresh! Cool, clean and confident – discover your invigorating match."
            else -> "Here's your perfect scent match!"
        }

        perfumeNameText.text = perfumeName
        perfumeDescText.text = perfumeDesc

        val cleanPerfumeName = perfumeName.trim().lowercase()
        val matchedImageRes = perfumeImages.entries.firstOrNull { entry ->
            cleanPerfumeName.contains(entry.key)
        }?.value ?: perfumeImages["default"] ?: R.drawable.default_perfume

        perfumeImage.setImageResource(matchedImageRes)

        val imageAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_zoom)
        perfumeImage.startAnimation(imageAnimation)

        val cardAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up_fade_in)
        resultCard.startAnimation(cardAnimation)

        when (scentType.lowercase()) {
            "woody" -> rootLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.woody_background))
            "floral" -> rootLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.floral_background))
            "fresh" -> rootLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.fresh_background))
        }

        findViewById<MaterialButton>(R.id.btnBackToHome).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            })
            finish()
        }
    }
}