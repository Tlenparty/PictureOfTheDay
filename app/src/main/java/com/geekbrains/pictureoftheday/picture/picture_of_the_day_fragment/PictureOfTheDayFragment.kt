package com.geekbrains.pictureoftheday.picture.picture_of_the_day_fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.databinding.FragmentMainStartBinding
import com.geekbrains.pictureoftheday.picture.bottom_navigation_drawer_fragment.BottomNavigationDrawerFragment
import com.geekbrains.pictureoftheday.settings.settings_fragment.SettingsFragment
import com.geekbrains.pictureoftheday.viewpager.ViewPagerAdapter
import com.geekbrains.pictureoftheday.viewpager.ViewPagerItems
import com.google.android.material.tabs.TabLayoutMediator

class PictureOfTheDayFragment : Fragment() {
    private var _binding: FragmentMainStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        super.onViewCreated(view, savedInstanceState)
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
        setTableLayout()
    }

    private fun setTableLayout() {
        binding.tableLayout.apply {
            viewPager.adapter = ViewPagerAdapter(this@PictureOfTheDayFragment)
            indicator.setViewPager(viewPager)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    1 -> resources.getString(R.string.yesterday)
                    2 -> resources.getString(R.string.today)
                    else -> resources.getString(R.string.before_yesterday)
                }
            }.attach()
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    when (position) {
                        1 -> ViewPagerItems.newInstance("YESTERDAY")
                        2 -> ViewPagerItems.newInstance("TODAY")
                        else -> ViewPagerItems.newInstance("BEFORE_YESTERDAY")
                    }
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> toast("Favourite")
            R.id.app_bar_settings -> activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, SettingsFragment())?.addToBackStack(null)?.commit()
            android.R.id.home -> {
                activity?.let {
                    BottomNavigationDrawerFragment().show(it.supportFragmentManager, "tag")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }
}
