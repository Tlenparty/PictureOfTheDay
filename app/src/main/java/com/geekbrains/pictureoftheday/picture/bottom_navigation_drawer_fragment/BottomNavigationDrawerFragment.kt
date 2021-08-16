package com.geekbrains.pictureoftheday.picture.bottom_navigation_drawer_fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.animations.AnimationsActivity
import com.geekbrains.pictureoftheday.animations.AnimationsActivityBonus
import com.geekbrains.pictureoftheday.databinding.BottomNavigationLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {
    private lateinit var binding: BottomNavigationLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomNavigationLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_one -> activity?.let{
                    startActivity(Intent(it, AnimationsActivity::class.java))
                }
                R.id.navigation_two -> activity?.let{
                    startActivity((Intent(it,AnimationsActivityBonus::class.java)))
                }
            }
            dismiss()
            true
        }
    }
}
