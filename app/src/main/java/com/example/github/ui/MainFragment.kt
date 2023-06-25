package com.example.github.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.github.R
import com.example.github.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController
    private lateinit var pref: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        pref = requireActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE)

        Log.d("TTTT", "Code:" + pref.getString("code", "NULL").toString())
        try {
            navController =
                (childFragmentManager.findFragmentById(R.id.fragment_container_view_menu) as NavHostFragment).navController

            binding.bottomNavigationView.setupWithNavController(navController)
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
}