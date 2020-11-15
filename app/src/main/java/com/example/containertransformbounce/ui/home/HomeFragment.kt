package com.example.containertransformbounce.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.containertransformbounce.R
import com.example.containertransformbounce.model.Sample
import com.example.containertransformbounce.databinding.FragmentHomeBinding
import com.example.containertransformbounce.ui.MainActivity
import com.example.containertransformbounce.ui.pager.PagerFragmentDirections
import com.google.android.material.transition.Hold

class HomeFragment : Fragment(), HomeAdapter.HomeAdapterListener {
    private lateinit var binding: FragmentHomeBinding
    private val homeAdapter by lazy {
        HomeAdapter(this)
    }

    private val sampleList by lazy {
        ArrayList<Sample>().apply {
            add(
                Sample(
                    id = 0,
                    title = "Sample Item 0's title. Just filling out title with random text.",
                    description = getString(
                        R.string.sample_description
                    )
                )
            )
            add(
                Sample(
                    id = 1,
                    title = "Sample Item 1's title. Just filling out title with random text.",
                    description = getString(
                        R.string.sample_description
                    )
                )
            )
            add(
                Sample(
                    id = 2,
                    title = "Sample Item 2's title. Just filling out title with random text.",
                    description = getString(
                        R.string.sample_description
                    )
                )
            )
            add(
                Sample(
                    id = 3,
                    title = "Sample Item 3's title. Just filling out title with random text.",
                    description = getString(
                        R.string.sample_description
                    )
                )
            )
            add(
                Sample(
                    id = 4,
                    title = "Sample Item 4's title. Just filling out title with random text.",
                    description = getString(
                        R.string.sample_description
                    )
                )
            )
            add(
                Sample(
                    id = 5,
                    title = "Sample Item 5's title. Just filling out title with random text.",
                    description = getString(
                        R.string.sample_description
                    )
                )
            )
            add(
                Sample(
                    id = 6,
                    title = "Sample Item 6's title. Just filling out title with random text.",
                    description = getString(
                        R.string.sample_description
                    )
                )
            )
            add(
                Sample(
                    id = 7,
                    title = "Sample Item 7's title. Just filling out title with random text.",
                    description = getString(
                        R.string.sample_description
                    )
                )
            )
            add(
                Sample(
                    id = 8,
                    title = "Sample Item 8's title. Just filling out title with random text.",
                    description = getString(
                        R.string.sample_description
                    )
                )
            )
            add(
                Sample(
                    id = 9,
                    title = "Sample Item 9's title. Just filling out title with random text.",
                    description = getString(
                        R.string.sample_description
                    )
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            homeRecyclerView.adapter = homeAdapter
            homeAdapter.submitList(sampleList)
        }
    }

    override fun onSampleItemClick(view: View, sample: Sample) {
        (requireActivity() as MainActivity).currentNavigationFragment?.exitTransition = Hold()
        val extras = FragmentNavigatorExtras(view to view.transitionName)
        findNavController().navigate(
            PagerFragmentDirections.actionPagerFragmentToDetailFragment(
                sample = sample
            ), extras
        )
    }

}