package com.example.containertransformbounce.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.containertransformbounce.R
import com.example.containertransformbounce.databinding.ActivityMainBinding
import com.example.containertransformbounce.ui.pager.PagerFragmentDirections

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {
    private lateinit var binding: ActivityMainBinding

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    private val inputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    val currentNavigationFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            ?.childFragmentManager
            ?.fragments
            ?.first()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting up actionbar.
        setSupportActionBar(binding.toolbar)
        binding.toolbar.apply {
            title = ""
            setNavigationOnClickListener { navController.navigateUp() }
        }

        // Add a focus listener on search EditText for navigation to SearchFragment.
        binding.searchEditText.setOnFocusChangeListener { v, hasFocus ->
            // Navigate to SearchFragment only if it isn't opened yet.
            if (hasFocus && navController.currentDestination?.id != R.id.searchFragment)
                navController.navigate(PagerFragmentDirections.actionGlobalSearchFragment())
        }

        // Setup destination listener.
        navController.addOnDestinationChangedListener(this)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        binding.run {
            // Change the navigationMenu icon to back button and update it's
            // click listener to navigate up if the destination is SearchFragment.
            updateMenuIconAndClickListener(isSearchFragment = destination.id == R.id.searchFragment)

            when (destination.id) {
                R.id.pagerFragment -> {
                    toolbar.navigationIcon = null
                    hideAppBar = false
                    hideSearch = false
                    hideTabs = false
                }
                R.id.detailFragment -> {
                    toolbar.navigationIcon = ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.ic_outline_arrow_back_24
                    )
                    hideAppBar = false
                    hideSearch = true
                    hideTabs = true
                }
                R.id.searchFragment -> {
                    hideAppBar = false
                    hideSearch = false
                    hideTabs = true
                }
            }
        }
    }

    private fun updateMenuIconAndClickListener(isSearchFragment: Boolean) {
        if (isSearchFragment)
            binding.navMenu.apply {
                setImageResource(R.drawable.ic_outline_arrow_back_24)
                setOnClickListener {
                    navController.navigateUp()
                }
            }
        else
            binding.navMenu.apply {
                // Clear focus and hide keyboard.
                binding.searchEditText.clearFocus()
                inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)

                // Set back hamburger menu icon and it's listener to null.
                setImageResource(R.drawable.ic_outline_menu_24)
                setOnClickListener { null }
            }
    }
}