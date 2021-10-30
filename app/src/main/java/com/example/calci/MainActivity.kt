package com.example.calci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.calci.databinding.ActivityMainBinding
import java.lang.ArithmeticException
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


    var lastDigit: Boolean = false
    fun onDigit(view: View){
       binding.tvInput.append((view as Button).text)
       lastDigit = true
    }

    fun onCLR (view: View){
        binding.tvInput.text = ""
        operatorAdded = false

        dotPresent = false

    }
    var dotPresent: Boolean = false

    fun onDecimal(view: View) {
        if (!dotPresent && lastDigit) {
            binding.tvInput.append(".")
            dotPresent = true
            lastDigit = false
            }
        /*if (!dotPresent && lastDigit && operatorAdded){
            binding.tvInput.append(".")
            dotPresent = true
            lastDigit = false
            }*/
        //    operatorAdded = false

    }

    var operatorAdded: Boolean = false
    var operationToDo = "x"
    fun onOperator(view: android.view.View) {
        if (!operatorAdded && binding.tvInput.text.isNotEmpty()){
            binding.tvInput.append((view as Button).text)
            operationToDo = (view as Button).text.toString()
            operatorAdded = true
            dotPresent = false
            lastDigit = false

        }
    }

    fun removeExcessiveZeroes(value: String) : String{
        //var shortenedResult = value.substring(0, 11)
        var shortenedResult = value
        when (value.length) { !in 0..11 -> shortenedResult = value.substring(0, 11)}


        Toast.makeText(this@MainActivity, "Trying to shorten: $shortenedResult", Toast.LENGTH_SHORT).show()
        //TimeUnit.SECONDS.sleep(5L)
        while (shortenedResult.endsWith("0")) {
            shortenedResult = shortenedResult.substring(0, shortenedResult.length -1)

            Toast.makeText(this@MainActivity, "Shortening: $shortenedResult", Toast.LENGTH_SHORT).show()
        }
        if (shortenedResult.endsWith(".")){
            shortenedResult = shortenedResult.substring(0, shortenedResult.length -1)
        }
        //    shortenedResult = shortenedResult
        return shortenedResult
    }

    fun onEquals(view: android.view.View) {
        if (lastDigit) {
            var tvValue = binding.tvInput.text.toString()

            try {


                val firstNumber = tvValue.substringBeforeLast(operationToDo)
                val secondNumber = tvValue.substringAfterLast(operationToDo)
                var result: String = ""

                when (operationToDo){

                    "-" -> result = (firstNumber.toDouble() - secondNumber.toDouble()).toString()
                    "+" -> result = (firstNumber.toDouble() + secondNumber.toDouble()).toString()
                    "/" -> result = (firstNumber.toDouble() / secondNumber.toDouble()).toString()
                    "*" -> result = (firstNumber.toDouble() * secondNumber.toDouble()).toString()



                }
                binding.tvInput.text = removeExcessiveZeroes(result)
                //Toast.makeText(this@MainActivity, "a: $firstNumber, b: $secondNumber", Toast.LENGTH_SHORT).show()

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
            operatorAdded = false

        }

    }
}