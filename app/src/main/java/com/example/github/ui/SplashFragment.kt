package com.example.github.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.databinding.FragmentSplashBinding
import com.example.github.utils.getStringFromPref
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private lateinit var binding: FragmentSplashBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

        checkUser()
    }

    private fun checkUser() {
        val accessKey = getStringFromPref("access_key")
        MainScope().launch {
            delay(1000)
            if (accessKey.isNullOrEmpty().not() && accessKey != "access_key") {
                findNavController().navigate(R.id.action_splashFragment_to_homeMenuFragment)
            } else {
                MainScope().launch {
                    delay(1000)
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                }
            }
        }
    }
}