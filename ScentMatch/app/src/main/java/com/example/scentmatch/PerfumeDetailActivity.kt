package com.example.scentmatch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PerfumeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfume_detail)

        val imageView = findViewById<ImageView>(R.id.perfumeImage)
        val titleView = findViewById<TextView>(R.id.perfumeTitle)
        val descView = findViewById<TextView>(R.id.perfumeDescription)
        val buyBtn = findViewById<Button>(R.id.btnBuy)
        val backBtn = findViewById<Button>(R.id.btnBack)

        // Receive data from PreferencesActivity
        val perfumeName = intent.getStringExtra("name") ?: "Unknown Perfume"
        val perfumeDesc = intent.getStringExtra("desc") ?: "No description available."
        val perfumeImage = intent.getIntExtra("image", R.drawable.perfume1)

        // Set data to views
        titleView.text = perfumeName
        descView.text = perfumeDesc
        imageView.setImageResource(perfumeImage)

        backBtn.setOnClickListener {
            finish()
        }

        buyBtn.setOnClickListener {
            val quantityInput = findViewById<EditText>(R.id.editQuantity)
            val quantityStr = quantityInput.text.toString()

            if (quantityStr.isEmpty() || quantityStr.toIntOrNull() == null || quantityStr.toInt() <= 0) {
                Toast.makeText(this, "Please enter a valid quantity.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val quantity = quantityStr.toInt()
            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("name", perfumeName)
            intent.putExtra("desc", perfumeDesc)
            intent.putExtra("image", perfumeImage)
            intent.putExtra("price", 69.99) // fixed for now
            intent.putExtra("quantity", quantity)
            startActivity(intent)
        }
    }
}
