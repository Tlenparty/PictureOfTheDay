package com.geekbrains.pictureoftheday.animations

import android.content.Context
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.databinding.ActivityAnimationsBonusStartBinding

class AnimationsActivityBonus : AppCompatActivity() {
    private lateinit var binding: ActivityAnimationsBonusStartBinding
    private var show = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsBonusStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnBackgroundClickListener()
    }

    private fun setOnBackgroundClickListener() = with(binding) {
        backgroundImage.setOnClickListener {
            if (show) hideComponents()
            else showComponents()
        }
    }

    private fun hideComponents() = with(binding) {
        show = false

        val constraintSet = ConstraintSet()
        constraintSet.clone(applicationContext, R.layout.activity_animations_bonus_start)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(constraintContainer, transition)
        constraintSet.applyTo(constraintContainer)
    }

    private fun showComponents() = with(binding){
        show = true

        val constraintSet = ConstraintSet()
        constraintSet.clone(applicationContext, R.layout.activity_animations_bonus_end)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(constraintContainer, transition)
        constraintSet.applyTo(constraintContainer)
    }

}
