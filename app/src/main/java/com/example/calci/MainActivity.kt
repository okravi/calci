package com.example.calci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.calci.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


    fun onDigit(view: View){
       binding.tvInput.append((view as Button).text)

    }

    fun onCLR (view: View){
        binding.tvInput.text = ""

    }

    fun onDecimal(view: View) {
        if ("." !in (binding.tvInput.text) && (binding.tvInput.text.isNotEmpty())) {
            binding.tvInput.append(".")
        }
    }

}