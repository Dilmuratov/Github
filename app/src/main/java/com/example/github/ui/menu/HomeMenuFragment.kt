package com.example.github.ui.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.databinding.FragmentMenuHomeBinding
import com.example.github.ui.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeMenuFragment : Fragment(R.layout.fragment_menu_home) {
    private lateinit var binding: FragmentMenuHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuHomeBinding.bind(view)

        initObservers()

        initListeners()
    }

    private fun initObservers() {
        (requireActivity() as MainActivity).showBottomNavigationView()
    }

    private fun initListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            lifecycleScope.launch {
                delay(3000)
            }
            binding.swipeRefreshLayout.isRefreshing = false
        }

        binding.ivSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeMenuFragment_to_searchFragment)
        }

        binding.llMyWork4.setOnClickListener {
            findNavController().navigate(R.id.action_homeMenuFragment_to_repositoriesFragment)
        }
    }
}