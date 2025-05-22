package com.example.scentmatch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PreferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val button1 = findViewById<Button>(R.id.btn_perfume1)
        val button2 = findViewById<Button>(R.id.btn_perfume2)
        val button3 = findViewById<Button>(R.id.btn_perfume3)

        button1.setOnClickListener {
            openPerfumeDetail(
                name = "Sauvage Dior",
                desc = "A fresh aquatic fragrance that captures the essence of the sea breeze and coastal charm.",
                imageRes = R.drawable.perfume1
            )
        }

        button2.setOnClickListener {
            openPerfumeDetail(
                name = "Armani Gio",
                desc = "A crisp blend of citrus and marine notes, evoking Mediterranean freshness and timeless elegance.",
                imageRes = R.drawable.perfume2
            )
        }

        button3.setOnClickListener {
            openPerfumeDetail(
                name = "Tom Ford",
                desc = "A bold, luxurious oud fragrance with warm spices, perfect for evening elegance and sophistication.",
                imageRes = R.drawable.perfume3
            )
        }
    }

    private fun openPerfumeDetail(name: String, desc: String, imageRes: Int) {
        val intent = Intent(this, PerfumeDetailActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("desc", desc)
        intent.putExtra("image", imageRes)
        startActivity(intent)
    }
}
