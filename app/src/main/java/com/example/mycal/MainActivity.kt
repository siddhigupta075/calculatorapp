package com.example.mycal

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var previousCalculationTextView: TextView

    private var firstNumber = 0.0
    private var operation = ""
    private var isNewOperation = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)
        previousCalculationTextView = findViewById(R.id.previousCalculationTextView)


        val button0: Button = findViewById<Button>(R.id.btn0)
        val button1: Button = findViewById<Button>(R.id.btn1)
        val button2: Button = findViewById<Button>(R.id.btn2)
        val button3: Button = findViewById<Button>(R.id.btn3)
        val button4: Button = findViewById<Button>(R.id.btn4)
        val button5: Button = findViewById<Button>(R.id.btn5)
        val button6: Button = findViewById<Button>(R.id.btn6)
        val button7: Button = findViewById<Button>(R.id.btn7)
        val button8: Button = findViewById<Button>(R.id.btn8)
        val button9: Button = findViewById<Button>(R.id.btn9)
        val add: Button = findViewById(R.id.btnAdd)
        val subtract: Button = findViewById(R.id.btnSubtract)
        val multiply: Button = findViewById(R.id.btnMultiply)
        val divide: Button = findViewById(R.id.btnDivide)
        val equals: Button = findViewById(R.id.btnEquals)
        val clear: Button = findViewById(R.id.btnClear)
        val backspace: Button = findViewById(R.id.btnBackspace)
        val percent: Button = findViewById(R.id.btnPercent)
        val dot: Button = findViewById(R.id.btnDot)

        // number button click listeners
        button0.setOnClickListener { appendNumber("0") }
        button1.setOnClickListener { appendNumber("1") }
        button2.setOnClickListener { appendNumber("2") }
        button3.setOnClickListener { appendNumber("3") }
        button4.setOnClickListener { appendNumber("4") }
        button5.setOnClickListener { appendNumber("5") }
        button6.setOnClickListener { appendNumber("6") }
        button7.setOnClickListener { appendNumber("7") }
        button8.setOnClickListener { appendNumber("8") }
        button9.setOnClickListener { appendNumber("9") }
        dot.setOnClickListener { appendNumber(".") }

        // operator button-click listeners
        add.setOnClickListener { setOperation("+") }
        subtract.setOnClickListener { setOperation("-") }
        multiply.setOnClickListener { setOperation("*") }
        divide.setOnClickListener { setOperation("/") }
        percent.setOnClickListener { setOperation("%") }

        equals.setOnClickListener { calculateResult() }
        clear.setOnClickListener { clearCalculator() }
        backspace.setOnClickListener { backspace() }

    }

    private fun appendNumber(number: String) {
        if (isNewOperation) {
            resultTextView.text = number
            isNewOperation = false
        } else {
            resultTextView.text="${resultTextView.text}$number"
        }
    }
    private fun setOperation(op: String) {
        firstNumber = resultTextView.text.toString().toDouble()
        operation=op
        isNewOperation = true
        previousCalculationTextView.text = "$firstNumber $operation"
    }

    private fun calculateResult(){
        try {
            val secondNumber = resultTextView.text.toString().toDouble()
            val result = when (operation) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "*" -> firstNumber * secondNumber
                "/" -> firstNumber / secondNumber
                "%" -> firstNumber % secondNumber
                else -> secondNumber
            }
            previousCalculationTextView.text = "$firstNumber $operation $secondNumber ="
            resultTextView.text = result.toString()
            isNewOperation = true
        } catch(e: Exception){
            resultTextView.text = "Error"
        }
    }

    private fun clearCalculator() {
        resultTextView.text = "0"
        previousCalculationTextView.text = ""
        firstNumber = 0.0
        operation = ""
        isNewOperation = true
    }

    private fun backspace() {
        val currentText = resultTextView.text.toString()
        if (currentText.isNotEmpty() && currentText!="0.0" && currentText!="Error") {
            resultTextView.text = currentText.substring(0, currentText.length - 1)
        } else {
            Toast.makeText(this, "Invalid Operation", Toast.LENGTH_SHORT).show()
        }
    }
}