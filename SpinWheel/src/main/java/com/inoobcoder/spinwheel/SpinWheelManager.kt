package com.inoobcoder.spinwheel

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.widget.ImageView
import kotlin.random.Random

class SpinWheelManager {

    private var spinDuration: Long = 3000 // Default spin duration
    private var prizeList: List<String> = emptyList()
    private var spinWheel: ImageView? = null

//    private val prizeList = listOf(9, 10, 12, 7, 14, 4, 5, 11, 6, 13, 8, 14)

    fun attachSpinWheel(
        spinWheel: ImageView,
        prizeList: List<String>,
        duration: Long = 3000
    ) {
        this.spinDuration = duration
        this.prizeList = prizeList
        this.spinWheel = spinWheel

        // Initialize any setup if needed for the ImageView
    }

    // Start spin animation
    fun startSpin(resultListener: (String) -> Unit) {
        val sizeOfPrizeList = prizeList.size

        if (sizeOfPrizeList == 0) {
            throw IllegalStateException("Prize list cannot be empty.")
        } else {
            val eachContainerAngle = 360/sizeOfPrizeList
            val prizeIndex = Random.nextInt(0, sizeOfPrizeList)
            val selectedPrize = prizeList[prizeIndex]
            val targetAngle = 360 * 3 - (prizeIndex * eachContainerAngle) - eachContainerAngle/2

            val animator = ObjectAnimator.ofFloat(spinWheel, "rotation", 0f, targetAngle.toFloat())
            animator.duration = spinDuration
            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    // Animation end logic
//                    Toast.makeText(this@Spin, "$selectedPrize", Toast.LENGTH_SHORT).show()
                    resultListener(selectedPrize)
                }
            })
            animator.start()
        }
    }

    /*private fun spinWheel(duration: Long = 3000L) {
        val selectedPrize = winningPrizeList.random()
        val prizeIndex = prizeList.indexOf(selectedPrize)
        val targetAngle = 360 * 3 - (prizeIndex * 30) - 15 // Spin 3 full rotations

        // Create the spin animation
        val animator = ObjectAnimator.ofFloat(binding.spin, "rotation", 0f, targetAngle.toFloat())
        animator.duration = duration
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                // Animation end logic
//                Toast.makeText(this@Spin, "$selectedPrize", Toast.LENGTH_SHORT).show()
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.winningCoin.coin.text = "$selectedPrize"
                    binding.mainLayout.visibility = View.GONE
                    binding.winLayout.visibility = View.VISIBLE
                    binding.btnClaim.btnText.isEnabled = true
                }, 500)
            }
        })
        animator.start()
    }*/

}