package com.example.scentmatch

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class InstructionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructions)

        // Fade-in animation for smoother UX
        val container = findViewById<LinearLayout>(R.id.instructionsContainer)
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        container.startAnimation(fadeIn)

        // Go to HomeActivity
        val gotItBtn = findViewById<Button>(R.id.btnGotIt)
        gotItBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }
    }
}
