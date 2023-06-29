package com.example.github.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.databinding.FragmentRepositoriesBinding
import com.example.github.presentation.MainViewModel
import com.example.github.ui.adapters.RepositoryMainAdapter
import com.example.github.utils.logTag
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoriesFragment : Fragment(R.layout.fragment_repositories) {
    private lateinit var binding: FragmentRepositoriesBinding
    private val adapter = RepositoryMainAdapter()
    private val mainViewModel: MainViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRepositoriesBinding.bind(view)
        binding.recyclerView.adapter = adapter

        getData()

        initListeners()

        initObservers()
    }

    private fun getData() {
        MainScope().launch {
            binding.swipeRefreshLayout.isRefreshing = true
            mainViewModel.getUserRepositories()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initObservers() {
        mainViewModel.getUserRepositoriesLiveData.observe(requireActivity()) {
            if (it != null) {
                adapter.submitList(it)
            } else Log.d(logTag, "Repository list is null")
        }

        mainViewModel.messageLiveData.observe(requireActivity()) {
            try {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        mainViewModel.errorLiveData.observe(requireActivity()) {
            it.printStackTrace()
        }

        (requireActivity() as MainActivity).hideBottomNavigationView()

        binding.tvTitle.text = "Repositories"
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.swipeRefreshLayout.apply {
            setOnRefreshListener {
                isRefreshing = true
                lifecycleScope.launch {
                    mainViewModel.getUserRepositories()
                }
                isRefreshing = false
            }
        }
    }
}