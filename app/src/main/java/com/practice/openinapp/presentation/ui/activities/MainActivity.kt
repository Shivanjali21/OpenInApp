package com.practice.openinapp.presentation.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.practice.openinapp.R
import com.practice.openinapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var navController: NavController
    private lateinit var navHost: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContentView(binding.main)

        binding.apply {
            navHost = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
            navController = navHost.navController
            bottomNavigationView.setupWithNavController(navController)
           /* navHost.findNavController().addOnDestinationChangedListener {_, destination, _ ->
                when(destination.id) {
                    R.id.linkFragment, R.id.coursesFragment, R.id.campaignsFragment -> bottomNavigationView.visibility = View.VISIBLE
                    else -> bottomNavigationView.visibility = View.GONE
                }
            }*/
        }
    }
}