package com.example.github.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.data.models.HistoryOfSearchData
import com.example.github.databinding.FragmentSearchBinding
import com.example.github.presentation.HistoryViewModel
import com.example.github.presentation.MainViewModel
import com.example.github.ui.adapters.HistoryAdapter
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
    private val historyAdapter = HistoryAdapter()
    private val mainViewModel: MainViewModel by viewModel()
    private val historyViewModel: HistoryViewModel by viewModel()
    private var isFull = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        setAdapters()

        initListeners()

        initObservers()

        (requireActivity() as MainActivity).hideBottomNavigationView()

        getAllHistory()

    }

    private fun setAdapters() {
        binding.recyclerViewRepositories.adapter = repositoryAdapter
        binding.recyclerViewUser.adapter = userAdapter
        binding.historyRecyclerView.adapter = historyAdapter
    }

    private fun initObservers() {
        mainViewModel.searchRepositoriesLiveData.observe(requireActivity()) {
            if (it != null) {
                val resulList = it.items
                if (resulList.size < 3 || isFull) {
                    repositoryAdapter.submitList(resulList)
                } else {
                    repositoryAdapter.submitList(resulList.take(3))
                }
            } else Log.d(logTag, "SearchResponseData is null")
        }

        mainViewModel.searchUsersLiveData.observe(requireActivity()) {
            if (it != null) {
                val resulList = it.items
                if (resulList.size < 3 || isFull) {
                    userAdapter.submitList(resulList)
                } else {
                    userAdapter.submitList(resulList.take(3))
                }
            } else Log.d(logTag, "SearchResponseData is null")
        }

        mainViewModel.messageLiveData.observe(requireActivity()) {
            Toast.makeText(requireActivity(), it.toString(), Toast.LENGTH_SHORT).show()
        }

        mainViewModel.errorLiveData.observe(requireActivity()) {
            it.printStackTrace()
        }

        historyViewModel.allDataLiveData.observe(requireActivity()) {
            if (it.isNotEmpty()) {
                historyAdapter.submitList(it.reversed())
                showOnlyHistory()
            } else {
                showOnlyDescriptions()
            }
        }
    }

    private fun initListeners() {
        binding.etSearch.addTextChangedListener {
            val q = it.toString()
            if (q.isNotEmpty()) {
                showOnlyComponents()
                fillWithQueryText(q)
            } else {
                if (historyAdapter.currentList.isEmpty()) {
                    showOnlyDescriptions()
                } else {
                    getAllHistory()
                    showOnlyHistory()
                }
            }
        }

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val q = binding.etSearch.text.toString()
                if (q.isNotEmpty()) {
                    isFull = false
                    searchRepositoriesAndUsers(q)
                    showUserAndRepository()
                }
                true
            } else false
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.llRepository.setOnClickListener {
            val q = binding.etSearch.text.toString()
            isFull = true
            showOnlyRepositories()
            searchRepositories(q)
        }

        binding.tvSeeMoreRepositories.setOnClickListener {
            isFull = true
            val q = binding.etSearch.text.toString()
            showOnlyRepositories()
            searchRepositories(q)
        }

        binding.llPeople.setOnClickListener {
            isFull = true
            val q = binding.etSearch.text.toString()
            showOnlyUsers()
            searchUsers(q)
        }

        binding.tvSeeMoreUser.setOnClickListener {
            isFull = true
            val q = binding.etSearch.text.toString()
            showOnlyUsers()
            searchUsers(q)
        }

        historyAdapter.setOnClearClickListener {
            MainScope().launch {
                historyViewModel.deleteData(it)
                historyAdapter.removeItem(it)
            }
        }
    }

    private fun getAllHistory() {
        MainScope().launch {
            historyViewModel.getAllData()
        }
    }

    private fun searchUsers(q: String) {
        MainScope().launch {
            mainViewModel.searchUsers(q)
            historyViewModel.addData(HistoryOfSearchData(0, q))
        }
    }

    private fun searchRepositories(q: String) {
        MainScope().launch {
            mainViewModel.searchRepositories(q)
            historyViewModel.addData(HistoryOfSearchData(0, q))
        }
    }

    private fun searchRepositoriesAndUsers(q: String) {
        MainScope().launch {
            mainViewModel.searchRepositories(q)
            mainViewModel.searchUsers(q)
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

    private fun showOnlyRepositories() {
        binding.llSearchComponents.hide()
        binding.llDescription.hide()
        binding.tvHistory.hide()
        binding.historyRecyclerView.hide()
        binding.llUsersResult.hide()
        binding.llRepositoriesResult.show()
        binding.tvRepositories.show()
        binding.recyclerViewRepositories.show()
        binding.tvSeeMoreRepositories.hide()
    }

    private fun showOnlyUsers() {
        binding.llSearchComponents.hide()
        binding.llDescription.hide()
        binding.tvHistory.hide()
        binding.historyRecyclerView.hide()
        binding.tvRepositories.hide()
        binding.recyclerViewRepositories.hide()
        binding.tvSeeMoreRepositories.hide()
        binding.llUsersResult.show()
        binding.tvUser.show()
        binding.recyclerViewUser.show()
        binding.tvSeeMoreUser.hide()
    }

    private fun showUserAndRepository() {
        binding.llSearchComponents.hide()
        binding.llDescription.hide()
        binding.tvHistory.hide()
        binding.historyRecyclerView.hide()
        binding.llRepositoriesResult.show()
        binding.llUsersResult.show()
    }

    private fun showOnlyHistory() {
        binding.llSearchComponents.hide()
        binding.llDescription.hide()
        binding.tvHistory.show()
        binding.historyRecyclerView.show()
        binding.llRepositoriesResult.hide()
        binding.llUsersResult.hide()
    }

    private fun showOnlyComponents() {
        binding.llSearchComponents.show()
        binding.llDescription.hide()
        binding.tvHistory.hide()
        binding.historyRecyclerView.hide()
        binding.llRepositoriesResult.hide()
        binding.llUsersResult.hide()
    }

    private fun showOnlyDescriptions() {
        binding.llSearchComponents.hide()
        binding.llDescription.show()
        binding.tvHistory.hide()
        binding.historyRecyclerView.hide()
        binding.llRepositoriesResult.hide()
        binding.llUsersResult.hide()
    }
}