package com.geekbrains.pictureoftheday.framework.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.framework.ui.view.picture_of_the_day_fragment.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container,PictureOfTheDayFragment.newInstance())
                .commitAllowingStateLoss()
        }
    }
}