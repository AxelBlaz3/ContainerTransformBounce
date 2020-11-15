package com.example.containertransformbounce.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import com.example.containertransformbounce.databinding.FragmentPagerBinding
import com.example.containertransformbounce.ui.MainActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialElevationScale
import kotlinx.android.synthetic.main.activity_main.*

class PagerFragment : Fragment() {
    private lateinit var binding: FragmentPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialElevationScale(false)
        reenterTransition = MaterialElevationScale(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        setupTabs()
    }

    private fun setupTabs() {
        binding.run {
            homeViewPager.adapter =
                HomeStateAdapter(fragment = this@PagerFragment)
            TabLayoutMediator((requireActivity() as MainActivity).homeTabLayout, homeViewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "Item 0"
                    1 -> "Item 1"
                    2 -> "Item 2"
                    3 -> "Item 3"
                    4 -> "Item 4"
                    5 -> "Item 5"
                    6 -> "Item 6"
                    7 -> "Item 7"
                    else -> throw RuntimeException("${this@PagerFragment.javaClass.simpleName}: Unknown position - $position when setting up TabLayout")
                }
            }.attach()
        }
    }
}