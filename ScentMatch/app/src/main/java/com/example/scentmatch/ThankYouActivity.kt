package com.example.scentmatch

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

class ThankYouActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thank_you)

        val backHomeButton = findViewById<Button>(R.id.btnBackHome)

        // Manual back to home
        backHomeButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        val lottieAnimation = findViewById<LottieAnimationView>(R.id.lottieCelebration)
        lottieAnimation.playAnimation()

    }
}
