package com.example.scentmatch

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val imageView = findViewById<ImageView>(R.id.cartImage)
        val nameView = findViewById<TextView>(R.id.cartName)
        val quantityView = findViewById<TextView>(R.id.cartQuantity)
        val priceView = findViewById<TextView>(R.id.cartPrice)
        val totalView = findViewById<TextView>(R.id.cartTotal)
        val checkoutBtn = findViewById<Button>(R.id.btnProceedCheckout)

        val name = intent.getStringExtra("name") ?: "Perfume"
        val price = intent.getDoubleExtra("price", 69.99)
        val quantity = intent.getIntExtra("quantity", 1)
        val imageRes = intent.getIntExtra("image", R.drawable.perfume1)

        val total = price * quantity

        imageView.setImageResource(imageRes)
        nameView.text = name
        quantityView.text = "Quantity: $quantity"
        priceView.text = "Price per unit: $${String.format("%.2f", price)}"
        totalView.text = "Total: $${String.format("%.2f", total)}"

        checkoutBtn.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("image", imageRes)
            intent.putExtra("price", total) // total price passed
            startActivity(intent)
        }
    }
}