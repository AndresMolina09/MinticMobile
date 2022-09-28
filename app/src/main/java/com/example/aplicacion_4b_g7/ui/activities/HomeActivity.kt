package com.example.aplicacion_4b_g7.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.aplicacion_4b_g7.R
import com.example.aplicacion_4b_g7.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.homeToolbar)
    }

    override fun onStart() {
        super.onStart()
        val navController = findNavController(R.id.nav_host_home_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.locationFragment,
                R.id.profileFragment,
                R.id.specialistFragment
            )
        )
        binding.homeNavigation.setupWithNavController(navController)
        binding.homeToolbar.setupWithNavController(navController, appBarConfiguration)
    }
}