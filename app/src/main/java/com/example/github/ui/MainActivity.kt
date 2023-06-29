package com.example.github.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.github.R
import com.example.github.databinding.ActivityMainBinding
import com.example.github.utils.hide
import com.example.github.utils.show
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController =
            (supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment).navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    fun showBottomNavigationView() {
        val bnv: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bnv.show()
    }

    fun hideBottomNavigationView() {
        val bnv: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bnv.hide()
    }
}