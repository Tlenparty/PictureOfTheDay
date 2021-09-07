package com.geekbrains.pictureoftheday.api.earth_fragment

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.geekbrains.pictureoftheday.MainActivity
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.databinding.FragmentEarthBinding
import com.geekbrains.pictureoftheday.picture.bottom_navigation_drawer_fragment.BottomNavigationDrawerFragment
import com.google.android.material.bottomappbar.BottomAppBar

class EarthFragment : Fragment() {

    private lateinit var binding: FragmentEarthBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomAppBar(view)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = with(binding) {
        when(item.itemId){
            android.R.id.home -> {
                activity?.let{
                    BottomNavigationDrawerFragment().show(it.supportFragmentManager,"tag")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBottomAppBar(view: View) = with(binding) {
        val context = activity as MainActivity
        context.setSupportActionBar(bottomAppBar)
        setHasOptionsMenu(true)
        fab.setOnClickListener {
            if (isMain) {
                isMain = false
                bottomAppBar.navigationIcon = null
                bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_back_fab))
                bottomAppBar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
            } else {
                isMain = true
                bottomAppBar.navigationIcon =
                    ContextCompat.getDrawable(context, R.drawable.ic_hamburger_menu_bottom_bar)
                bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_plus_fab))
                bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
        }
    }

    companion object {
        fun newInstance() = EarthFragment()
        private var isMain = true
    }

}