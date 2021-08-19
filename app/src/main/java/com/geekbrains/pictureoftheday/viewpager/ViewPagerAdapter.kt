package com.geekbrains.pictureoftheday.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.geekbrains.pictureoftheday.api.earth_fragment.EarthFragment
import com.geekbrains.pictureoftheday.api.mars_fragment.MarsFragment
import com.geekbrains.pictureoftheday.api.weather_fragment.WeatherFragment

private const val EARTH_FRAGMENT = 0
private const val MARS_FRAGMENT = 1
private const val WEATHER_FRAGMENT = 2

class ViewPagerAdapter(
    private val fragmentManager: Fragment,
) : FragmentStateAdapter(fragmentManager) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> ViewPagerItems.newInstance("YESTERDAY")
            2 -> ViewPagerItems.newInstance("TODAY")
            else -> ViewPagerItems.newInstance("BEFORE_YESTERDAY")
        }
    }
}




