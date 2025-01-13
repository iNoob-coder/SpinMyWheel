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

    fun attachSpinWheel(
        spinWheel: ImageView,
        prizeList: List<String>,
        duration: Long = 3000
    ) {
        this.spinDuration = duration
        this.prizeList = prizeList
        this.spinWheel = spinWheel
    }

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
                    resultListener(selectedPrize)
                }
            })
            animator.start()
        }
    }

}