package com.example.scentmatch

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val prefsName = "UserPrefs" // renamed from PREFS_NAME

    @Suppress("SENSELESS_COMPARISON") // Optional: silences the false warning about null check
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences(prefsName, Context.MODE_PRIVATE)

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val skipBtn = findViewById<Button>(R.id.skipBtn)
        val emailInput = findViewById<EditText>(R.id.emailInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)

        loginBtn.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                val savedPassword = sharedPreferences.getString(email, null)

                if (savedPassword == null) {
                    // First time user — save credentials
                    sharedPreferences.edit().putString(email, password).apply()
                    Toast.makeText(this, "Account created. Logged in!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {
                    if (savedPassword == password) {
                        // Correct login
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    } else {
                        // Password mismatch
                        Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        skipBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}
