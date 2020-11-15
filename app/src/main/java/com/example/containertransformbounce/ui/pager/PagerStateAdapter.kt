package com.example.containertransformbounce.ui.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.containertransformbounce.ui.home.HomeFragment

private const val TABS_COUNT = 8

class HomeStateAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = TABS_COUNT

    override fun createFragment(position: Int): Fragment =
        HomeFragment()
}