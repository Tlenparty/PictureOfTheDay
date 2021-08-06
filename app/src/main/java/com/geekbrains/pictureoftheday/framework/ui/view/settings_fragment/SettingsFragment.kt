package com.geekbrains.pictureoftheday.framework.ui.view.settings_fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.databinding.FragmentSettingsBinding
import com.geekbrains.pictureoftheday.framework.ui.MainActivity
import com.google.android.material.chip.Chip

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        when (MainActivity.ThemeHolder.theme) {
            R.style.MySpaceTheme -> radioGroup.check(R.id.radio_button_space_theme)
            R.style.MyPinkPlanetTheme -> radioGroup.check(R.id.radio_button_pink_theme)
        }
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_button_space_theme -> {
                    MainActivity.ThemeHolder.theme = R.style.MySpaceTheme
                }
                R.id.radio_button_pink_theme -> {
                    MainActivity.ThemeHolder.theme = R.style.MyPinkPlanetTheme
                }
            }
        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.popBackStack()
                requireActivity().recreate()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
