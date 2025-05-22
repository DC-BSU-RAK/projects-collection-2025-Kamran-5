package com.example.scentmatch

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.button.MaterialButton
import com.google.android.material.radiobutton.MaterialRadioButton
import android.widget.Toast

class QuizActivity : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var option1: MaterialRadioButton
    private lateinit var option2: MaterialRadioButton
    private lateinit var option3: MaterialRadioButton
    private lateinit var nextButton: MaterialButton
    private lateinit var questionCard: CardView
    private lateinit var questionProgressText: TextView

    private var currentQuestionIndex = 0
    private val answers = mutableListOf<Int>()

    private val questions = listOf(
        "Which type of scent appeals most to you?",
        "What kind of environment do you find most relaxing?",
        "Which word best describes your ideal mood?",
        "What time of day do you feel most inspired?"
    )

    private val options = listOf(
        listOf("Floral - light, sweet, romantic", "Woody - earthy, warm, sophisticated", "Fresh - clean, crisp, invigorating"),
        listOf("A lush, green forest", "A serene, calm beach", "A vibrant, bustling city"),
        listOf("Peaceful & tranquil", "Energetic & bold", "Comforting & cozy"),
        listOf("Early morning, with a new start", "Mid-day, productive and active", "Late evening, introspective and quiet")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionText = findViewById(R.id.questionText)
        radioGroup = findViewById(R.id.radioGroup)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        nextButton = findViewById(R.id.nextButton)
        questionCard = findViewById(R.id.questionCard)
        questionProgressText = findViewById(R.id.questionProgressText)

        loadQuestion()

        nextButton.setOnClickListener {
            if (radioGroup.checkedRadioButtonId == -1) {
                // No option selected
                return@setOnClickListener showMessage("Please select an option to proceed.")
            }

            val selectedIndex = when (radioGroup.checkedRadioButtonId) {
                R.id.option1 -> 0
                R.id.option2 -> 1
                R.id.option3 -> 2
                else -> -1
            }

            if (selectedIndex == -1) return@setOnClickListener

            if (answers.size > currentQuestionIndex) {
                answers[currentQuestionIndex] = selectedIndex
            } else {
                answers.add(selectedIndex)
            }

            currentQuestionIndex++

            if (currentQuestionIndex < questions.size) {
                loadQuestion()
            } else {
                showResults()
            }
        }
    }

    private fun loadQuestion() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in_slide_up)
        questionCard.startAnimation(animation)

        questionText.text = questions[currentQuestionIndex]
        option1.text = options[currentQuestionIndex][0]
        option2.text = options[currentQuestionIndex][1]
        option3.text = options[currentQuestionIndex][2]

        radioGroup.clearCheck()

        questionProgressText.text = "Question ${currentQuestionIndex + 1} of ${questions.size}"

        nextButton.text = if (currentQuestionIndex == questions.size - 1) "Finish" else "Next"
    }

    private fun showResults() {
        val counts = IntArray(3) { 0 }
        for (answer in answers) {
            counts[answer]++
        }

        val maxIndex = counts.indices.maxByOrNull { counts[it] } ?: 0

        val scentTypes = listOf("Floral", "Woody", "Fresh")
        // REMOVED 'private' MODIFIER HERE
        val realPerfumes = listOf(
            Pair("Dior J'adore", "A luxurious floral fragrance with notes of jasmine, rose, and ylang-ylang."),
            Pair("Chanel Bleu de Chanel", "A bold woody scent that reflects confidence and sophistication, with notes of cedar and amber."),
            Pair("Versace Dylan Blue", "A fresh, aquatic fragrance for a modern, vibrant vibe, featuring hints of fig leaves and ambroxan.")
        )


        val selectedScent = scentTypes[maxIndex]
        val perfumeName = realPerfumes[maxIndex].first
        val perfumeDesc = realPerfumes[maxIndex].second

        val intent = Intent(this, QuizResultActivity::class.java)
        intent.putExtra("SCENT_TYPE", selectedScent)
        intent.putExtra("PERFUME_NAME", perfumeName)
        intent.putExtra("PERFUME_DESC", perfumeDesc)
        startActivity(intent)
        finish()
    }

    private fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}