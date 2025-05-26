package com.example.noteapp.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardBinding
import com.example.noteapp.ui.adapters.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()

        val adapter = PagerAdapter(this)
            binding.viewPager.adapter = adapter

                binding.dotsIndicator.attachTo(binding.viewPager)

    }

    private fun initialize() {
        binding.viewPager.adapter = PagerAdapter(this)
    }

    private fun setupListeners() = with(binding.viewPager) {
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
    }
}