package com.inoobcoder.spinanimation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.inoobcoder.spinanimation.databinding.ActivityMainBinding
import com.inoobcoder.spinwheel.SpinWheelManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val prizeList =
        listOf("9", "10", "12", "7", "14", "4", "5", "11", "6", "13", "8", "14")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinWheelManager = SpinWheelManager()
        spinWheelManager.attachSpinWheel(binding.spin, prizeList, 3000)

        binding.btnSpin.setOnClickListener {
            spinWheelManager.startSpin { result ->
                Toast.makeText(this, "You won: $result", Toast.LENGTH_SHORT).show()
            }
        }
    }
}