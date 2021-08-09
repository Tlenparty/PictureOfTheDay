package com.geekbrains.pictureoftheday

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.annotation.StyleRes
import com.geekbrains.pictureoftheday.api.earth_fragment.EarthFragment
import com.geekbrains.pictureoftheday.api.mars_fragment.MarsFragment
import com.geekbrains.pictureoftheday.api.weather_fragment.WeatherFragment
import com.geekbrains.pictureoftheday.databinding.MainActivityBinding
import com.geekbrains.pictureoftheday.picture.picture_of_the_day_fragment.PictureOfTheDayFragment
import com.geekbrains.pictureoftheday.settings.settings_fragment.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

const val THEME_PREFERENCE = "Theme"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private lateinit var themePreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        themePreference = getSharedPreferences(THEME_PREFERENCE, Context.MODE_PRIVATE)
        binding = MainActivityBinding.inflate(layoutInflater)
        if (themePreference.contains(THEME_PREFERENCE)) {
            ThemeHolder.theme = (themePreference.getInt(THEME_PREFERENCE, R.style.AppTheme));
        }
        setTheme(ThemeHolder.theme)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment())
                .commitAllowingStateLoss()
        }

        setBottomNavigation()
        //setBottomNavigationView(findViewById(R.id.bottom_navigation_view))
    }

    private fun setBottomNavigation() = with(binding) {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_main -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container,PictureOfTheDayFragment())
                        .commitAllowingStateLoss()
                     true
                }

                R.id.bottom_view_earth -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, EarthFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_mars -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MarsFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_weather -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, WeatherFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_settings -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container,SettingsFragment())
                        .commitAllowingStateLoss()
                    true
                }
                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, EarthFragment())
                        .commitAllowingStateLoss()
                    true

                }
            }
        }
    }

        private fun setBottomNavigationView(layout: LinearLayout) {
            val navigation = layout.findViewById<BottomNavigationView>(R.id.navigation_view)
            navigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.bottom_view_settings -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, SettingsFragment.newInstance())
                            .addToBackStack(null)
                            .commit()
                        true
                    }
                    R.id.bottom_view_mars -> {
                        true
                    }
                    R.id.bottom_view_weather -> {
                        true
                    }
                    R.id.bottom_view_main -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                            .addToBackStack(null)
                            .commit()
                        true
                    }
                    else -> false
                }
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