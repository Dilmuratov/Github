package com.example.github.ui
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.databinding.FragmentSplashBinding

class SplashFragment: Fragment(R.layout.fragment_splash) {
    private lateinit var binding: FragmentSplashBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

        binding.root.setOnClickListener {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }
}