package com.example.poetrygeneratorcalculator

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private lateinit var symbolGrid: GridLayout
    private lateinit var inputSymbols: TextView
    private lateinit var outputPoem: TextView
    private val selectedSymbols = mutableListOf<String>()

    private val symbolMap = mapOf(
        "❤" to "Love burns quietly in twilight.",
        "🔥" to "Flames rise within the heart.",
        "🌧" to "Rain whispers forgotten tales.",
        "🌙" to "The moon watches in silence.",
        "🍃" to "Leaves dance through ancient winds.",
        "🌸" to "Petals fall like soft goodbyes.",
        "🕊" to "A dove floats through fading skies.",
        "❄" to "Frost kisses the morning light.",
        "🌟" to "Stars sparkle in the night sky."
    )

    private val symbolColors = mapOf(
        "❤" to "#B71C1C",   // dark red
        "🔥" to "#E65100",   // burnt orange
        "🌧" to "#455A64",   // blue grey
        "🌙" to "#263238",   // dark blue
        "🍃" to "#1B5E20",   // dark green
        "🌸" to "#880E4F",   // deep pink
        "🕊" to "#37474F",   // dark grey blue
        "❄" to "#01579B",   // icy blue
        "🌟" to "#F9A825"    // golden yellow
    )

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load GIF background using Glide
        val gifBackground = findViewById<ImageView>(R.id.gifBackground)
        Glide.with(this)
            .asGif()
            .load(R.raw.background)  // Make sure background.gif is placed in res/raw/
            .into(gifBackground)

        symbolGrid = findViewById(R.id.symbolGrid)
        inputSymbols = findViewById(R.id.inputSymbols)
        outputPoem = findViewById(R.id.outputPoem)

        val clearBtn = findViewById<Button>(R.id.clearBtn)
        val generateBtn = findViewById<Button>(R.id.generateBtn)
        val infoBtn = findViewById<Button>(R.id.infoBtn)

        // Load animations
        val buttonClickAnimation = AnimationUtils.loadAnimation(this, R.anim.button_click)

        setupSymbolButtons()
        val slideUpAnim = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        for (i in 0 until symbolGrid.childCount) {
            symbolGrid.getChildAt(i).startAnimation(slideUpAnim)
        }

        clearBtn.setOnClickListener {
            it.startAnimation(buttonClickAnimation)
            selectedSymbols.clear()
            inputSymbols.text = "Your Selection:"
            outputPoem.text = ""
        }

        generateBtn.setOnClickListener {
            it.startAnimation(buttonClickAnimation)
            val fadeInAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in)

            if (selectedSymbols.isEmpty()) {
                outputPoem.text = "Please select symbols first! Tap on a symbol to start."
                return@setOnClickListener
            }

            outputPoem.text = generatePoem()
            outputPoem.startAnimation(fadeInAnim)
        }

        infoBtn.setOnClickListener {
            it.startAnimation(buttonClickAnimation)
            showInstructionModal()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupSymbolButtons() {
        val size = resources.getDimensionPixelSize(R.dimen.symbol_button_size)
        val margin = resources.getDimensionPixelSize(R.dimen.symbol_button_margin)

        symbolMap.keys.forEach { symbol ->
            val btn = Button(this)
            btn.text = symbol
            btn.textSize = 32f
            btn.setTextColor(Color.WHITE)
            btn.background = getDrawable(R.drawable.symbol_button_bg)
            btn.background.setTint(Color.parseColor(symbolColors[symbol]))

            val params = GridLayout.LayoutParams().apply {
                width = size
                height = size
                setMargins(margin, margin, margin, margin)
                setGravity(Gravity.CENTER)
            }
            btn.layoutParams = params

            btn.setOnClickListener {
                if (selectedSymbols.size == 4) {
                    selectedSymbols.removeAt(0)
                }
                selectedSymbols.add(symbol)
                inputSymbols.text = "Your Selection: ${selectedSymbols.joinToString(" ")}"
            }

            symbolGrid.addView(btn)
        }
    }

    private fun generatePoem(): String {
        return selectedSymbols.mapNotNull { symbolMap[it] }.joinToString("\n")
    }

    private fun showInstructionModal() {
        AlertDialog.Builder(this)
            .setTitle("How to Use")
            .setMessage(
                """
                Tap symbols to express feelings or themes.
                Each symbol adds a poetic line.

                ❤ Love
                🔥 Fire
                🌧 Rain
                🌙 Night
                🍃 Wind
                🌸 Bloom
                🕊 Peace
                ❄ Cold
                🌟 Stars

                Only 4 lines are shown at a time.
                """.trimIndent()
            )
            .setPositiveButton("Got it", null)
            .show()
    }
}
