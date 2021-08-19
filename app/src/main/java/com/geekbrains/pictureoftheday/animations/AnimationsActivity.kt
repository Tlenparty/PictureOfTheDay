package com.geekbrains.pictureoftheday.animations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.databinding.ActivityAnimationsFabBinding

class AnimationsActivity : AppCompatActivity() {
    private var isExpanded = false
    private lateinit var binding: ActivityAnimationsFabBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsFabBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFAB()
        setOnScrollChangeListener()
    }

    private fun setFAB() = with(binding) {
        setInitialState()
        fab.setOnClickListener {
            if (isExpanded) {
                collapseFab()
            } else {
                expandFAB()
            }
        }
    }

    private fun setInitialState() = with(binding) {
        transparentBackground.apply {
            alpha = 0f
        }
        optionTwoContainer.apply {
            alpha = 0f
            isClickable = false
        }
        optionOneContainer.apply {
            alpha = 0f
            isClickable = false
        }
    }

    @SuppressLint("NewApi")
    private fun setOnScrollChangeListener() = with(binding) {
        scrollView.setOnScrollChangeListener { _, _, _, _, _ ->
            toolbar.isSelected = scrollView.canScrollVertically(-1)
        }
    }

    private fun collapseFab() = with(binding) {
        isExpanded = false
        ObjectAnimator.ofFloat(plusImageview, "rotation", 0f, -180f).start()
        ObjectAnimator.ofFloat(optionTwoContainer, "translationY", 0f).start()

        optionTwoContainer.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    optionTwoContainer.isClickable = false
                    optionTwoContainer.setOnClickListener(null)
                }
            })
        optionOneContainer.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    optionOneContainer.isClickable = false
                }
            })
        transparentBackground.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    transparentBackground.isClickable = false
                }
            })
    }

    private fun expandFAB() = with(binding) {
        isExpanded = true
        ObjectAnimator.ofFloat(plusImageview, "rotation", 0f, 225f).start()
        ObjectAnimator.ofFloat(optionTwoContainer, "translationY", -130f).start()
        ObjectAnimator.ofFloat(optionOneContainer, "translationY", -250f).start()

        optionTwoContainer.animate()
            .alpha(1f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    optionTwoContainer.isClickable = true
                    optionTwoContainer.setOnClickListener {
                        Toast.makeText(this@AnimationsActivity, "Option 2", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        optionOneContainer.animate()
            .alpha(1f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    optionOneContainer.isClickable = true
                    optionOneContainer.setOnClickListener {
                        Toast.makeText(this@AnimationsActivity, "Option 1", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        transparentBackground.animate()
            .alpha(0.9f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    transparentBackground.isClickable = true
                }
            })
    }

}


