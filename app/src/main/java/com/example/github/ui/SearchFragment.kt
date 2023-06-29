package com.example.github.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.github.R
import com.example.github.data.models.searchrepositories.RepositoryItem
import com.example.github.data.models.searchusers.UserItem
import com.example.github.databinding.FragmentSearchBinding
import com.example.github.presentation.MainViewModel
import com.example.github.ui.adapters.SearchRepositoryAdapter
import com.example.github.ui.adapters.SearchUserAdapter
import com.example.github.utils.hide
import com.example.github.utils.logTag
import com.example.github.utils.show
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding
    private val repositoryAdapter = SearchRepositoryAdapter()
    private val userAdapter = SearchUserAdapter()
    private val mainViewModel: MainViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        binding.recyclerViewRepositories.adapter = repositoryAdapter
        binding.recyclerViewUser.adapter = userAdapter

        initListeners()

        initObservers()

        (requireActivity() as MainActivity).hideBottomNavigationView()

    }

    private fun initObservers() {
        mainViewModel.searchRepositoriesLiveData.observe(requireActivity()) {
            if (it != null) {
                val resulList = it.items
                if (resulList.size < 3) {
                    repositoryAdapter.submitList(resulList)
                } else {
                    val list = mutableListOf<RepositoryItem>()
                    repeat(3) { j ->
                        list.add(resulList[j])
                    }
                    repositoryAdapter.submitList(list)
                }
            } else Log.d(logTag, "SearchResponseData is null")
        }

        mainViewModel.searchUsersLiveData.observe(requireActivity()) {
            if (it != null) {
                val resulList = it.items
                if (resulList.size < 3) {
                    userAdapter.submitList(resulList)
                } else {
                    val list = mutableListOf<UserItem>()
                    repeat(3) { j ->
                        list.add(resulList[j])
                    }
                    userAdapter.submitList(list)
                }
                binding.llSearchComponents.hide()
                binding.llDescription.hide()
                binding.llRepositoriesResult.show()
                binding.llUsersResult.show()
            } else Log.d(logTag, "SearchResponseData is null")
        }

        mainViewModel.messageLiveData.observe(requireActivity()) {
            Toast.makeText(requireActivity(), it.toString(), Toast.LENGTH_SHORT).show()
        }

        mainViewModel.errorLiveData.observe(requireActivity()) {
            it.printStackTrace()
        }
    }

    private fun initListeners() {
        binding.etSearch.addTextChangedListener {
            val q = it.toString()
            if (q.isNotEmpty()) {
                binding.llSearchComponents.show()
                binding.tvDescription.hide()
                fillWithQueryText(q)
            } else {
                binding.llSearchComponents.hide()
                binding.tvDescription.show()
            }
        }

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val q = binding.etSearch.text.toString()
                if (q.isNotEmpty()) {
                    MainScope().launch {
                        mainViewModel.searchRepositories(q)
                        mainViewModel.searchUsers(q)
                    }
                }
                true
            } else false
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fillWithQueryText(q: String) {
        binding.tvRepository.text = "Repositories with '$q'"
        binding.tvIssues.text = "Issues with '$q'"
        binding.tvPullRequests.text = "Pull Requests with '$q'"
        binding.tvPeople.text = "People with '$q'"
        binding.tvOrganization.text = "Organization with '$q'"
        binding.tvJump.text = "Jump to '$q'"
    }
}