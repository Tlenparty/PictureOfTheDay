package com.geekbrains.pictureoftheday.framework.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StyleRes
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.databinding.MainActivityBinding
import com.geekbrains.pictureoftheday.framework.ui.view.picture_of_the_day_fragment.PictureOfTheDayFragment

const val THEME_PREFERENCE = "Theme"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private lateinit var themePreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        themePreference = getSharedPreferences(THEME_PREFERENCE, Context.MODE_PRIVATE)
        if (themePreference.contains(THEME_PREFERENCE)){
            ThemeHolder.theme = (themePreference.getInt(THEME_PREFERENCE, R.style.AppTheme));
        }
        setTheme(ThemeHolder.theme)
        setContentView(R.layout.main_activity)
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container,PictureOfTheDayFragment.newInstance())
                .commitAllowingStateLoss()
        }
    }

    override fun onStop() {
        super.onStop()
        val editor: SharedPreferences.Editor = themePreference.edit()
        editor.putInt(THEME_PREFERENCE, ThemeHolder.theme)
        editor.apply()
    }

    object ThemeHolder {
        @StyleRes
        var theme: Int = R.style.AppTheme
    }
}