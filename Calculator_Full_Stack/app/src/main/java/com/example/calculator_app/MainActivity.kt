// MainActivity.kt
package com.example.calculator_app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentExpression = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Đảm bảo tên layout đúng

        display = findViewById(R.id.display)

        // Tạo mảng các nút số và phép toán
        val buttonIds = intArrayOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.buttonAdd, R.id.buttonSubtract,
            R.id.buttonMultiply, R.id.buttonDivide, R.id.buttonDot
        )

        for (id in buttonIds) {
            findViewById<Button>(id).setOnClickListener { view ->
                val button = view as Button
                currentExpression.append(button.text)
                display.text = currentExpression.toString()
            }
        }

        // Nút CE - Clear All
        findViewById<Button>(R.id.buttonCE).setOnClickListener {
            currentExpression.clear()
            display.text = "0"
        }

        // Nút C - Clear Memory
        findViewById<Button>(R.id.buttonC).setOnClickListener {
            // Xóa bộ nhớ, không thay đổi giao diện
        }

        // Nút BS - Backspace
        findViewById<Button>(R.id.buttonBS).setOnClickListener {
            if (currentExpression.isNotEmpty()) {
                currentExpression.deleteCharAt(currentExpression.length - 1)
                display.text = if (currentExpression.isEmpty()) "0" else currentExpression.toString()
            }
        }
        // Nút % - Chia lấy phần dư
        findViewById<Button>(R.id.buttonPercent).setOnClickListener {
            val parts = currentExpression.toString().split("%")
            if (parts.size == 2) {
                val left = parts[0].toDoubleOrNull()
                val right = parts[1].toDoubleOrNull()

                if (left != null && right != null && right != 0.0) {
                    val result = left % right
                    display.text = result.toString()
                    currentExpression = StringBuilder(result.toString())
                } else {
                    display.text = "Error"
                }
            } else {
                display.text = "Error"
            }
        }


        // Nút = - Tính toán kết quả
        findViewById<Button>(R.id.buttonEqual).setOnClickListener {
            try {
                val expression: Expression = ExpressionBuilder(currentExpression.toString()).build()
                val result = expression.evaluate()

                // Kiểm tra xem kết quả có phải là số nguyên hay không
                display.text = if (result == result.toInt().toDouble()) {
                    result.toInt().toString() // Chỉ hiển thị phần nguyên
                } else {
                    result.toString()
                }

                currentExpression = StringBuilder(display.text.toString())
            } catch (e: Exception) {
                display.text = "Error"
                currentExpression.clear()
            }
        }

    }
}