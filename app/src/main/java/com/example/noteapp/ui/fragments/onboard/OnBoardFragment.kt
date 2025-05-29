package com.example.noteapp.ui.fragments.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp.databinding.FragmentOnBoardBinding
import com.example.noteapp.ui.adapters.PagerAdapter
import com.example.noteapp.utils.PreferenceHelper

class OnBoardFragment : Fragment() {

    private val binding by lazy {
        FragmentOnBoardBinding.inflate(layoutInflater)
    }
    companion object {
        var sharedPreferences = PreferenceHelper()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkFirstTime()
        initialize()
        setupListeners()

        val adapter = PagerAdapter(this)
        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)

    }

    private fun checkFirstTime() {
        sharedPreferences.unit(requireContext())
        if (sharedPreferences.isShowOnBoard) {
            val action = OnBoardFragmentDirections.actionOnBoardFragmentToNoteFragment()
            findNavController().navigate(action)
            onDestroyView()
        }
    }

    private fun initialize() {
        binding.viewPager.adapter = PagerAdapter(this)
    }

    private fun setupListeners() = with(binding.viewPager){
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) = with(binding){
                super.onPageSelected(position)
                if (position == 2) {
                    txtSkip.visibility = View.GONE
                    buttonBegin.visibility = View.VISIBLE
                }else{
                    txtSkip.visibility = View.VISIBLE
                    buttonBegin.visibility = View.GONE
                    txtSkip.setOnClickListener {
                        setCurrentItem(currentItem + 2, true)
                    }
                }
            }
        })

        binding.buttonBegin.setOnClickListener{
            if (!sharedPreferences.isShowOnBoard){
                val action = OnBoardFragmentDirections.actionOnBoardFragmentToNoteFragment()
                findNavController().navigate(action)
                sharedPreferences.isShowOnBoard = true
            }
        }
    }
}