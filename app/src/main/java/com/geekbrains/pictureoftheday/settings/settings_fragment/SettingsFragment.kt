package com.geekbrains.pictureoftheday.settings.settings_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.databinding.FragmentSettingsBinding
import com.geekbrains.pictureoftheday.MainActivity
import com.google.android.material.behavior.SwipeDismissBehavior
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams;

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
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

        val swipe: SwipeDismissBehavior<CardView?> = SwipeDismissBehavior<CardView?>()
        swipe.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY)
        swipe.listener = object : SwipeDismissBehavior.OnDismissListener {
            override fun onDismiss(view: View?) {
                Toast.makeText(
                    context,
                    "Card swiped !!", Toast.LENGTH_SHORT
                ).show()
            }
            override fun onDragStateChanged(state: Int) {}
        }

        val mCardView: CardView =  view.findViewById<CardView>(R.id.card_view)
        val coordinatorParams = mCardView.layoutParams as LayoutParams
        coordinatorParams.behavior = swipe

    requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.popBackStack()
                requireActivity().recreate()
            }
        })
    }

    companion object{
        fun newInstance() = SettingsFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
