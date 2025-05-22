package com.example.scentmatch

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val imageView = findViewById<ImageView>(R.id.checkoutImage)
        val titleView = findViewById<TextView>(R.id.checkoutTitle)
        val priceView = findViewById<TextView>(R.id.checkoutPrice)
        val nameInput = findViewById<EditText>(R.id.editName)
        val addressInput = findViewById<EditText>(R.id.editAddress)
        val cardInput = findViewById<EditText>(R.id.editFlat) // Flat Number input
        val confirmBtn = findViewById<Button>(R.id.btnConfirm)

        // Get perfume info passed via intent
        val name = intent.getStringExtra("name") ?: "Perfume"
        val price = intent.getDoubleExtra("price", 69.99)
        val imageRes = intent.getIntExtra("image", R.drawable.perfume1)

        titleView.text = name
        priceView.text = "Price: $${String.format("%.2f", price)}"
        imageView.setImageResource(imageRes)

        confirmBtn.setOnClickListener {
            val userName = nameInput.text.toString().trim()
            val userAddress = addressInput.text.toString().trim()
            val flatNumber = cardInput.text.toString().trim() // now used for Flat Number

            if (userName.isEmpty() || userAddress.isEmpty() || flatNumber.isEmpty()) {
                Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show()
            } else {
                // Move to Thank You screen
                val intent = Intent(this, ThankYouActivity::class.java)
                intent.putExtra("user", userName)
                startActivity(intent)
                finish() // optional: finish this activity so user can't go back
            }
        }
    }
}
