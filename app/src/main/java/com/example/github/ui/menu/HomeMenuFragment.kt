package com.example.github.ui.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.github.R
import com.example.github.data.models.MyWorksData
import com.example.github.databinding.FragmentMenuHomeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeMenuFragment : Fragment(R.layout.fragment_menu_home) {
    private lateinit var binding: FragmentMenuHomeBinding
    private var adapter = MyWorksAdapter()
    private var myWorkList = mutableListOf<MyWorksData>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuHomeBinding.bind(view)

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            lifecycleScope.launch {
                delay(3000)
            }
            binding.swipeRefreshLayout.isRefreshing = false
        }

        binding.recyclerView.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fillList()
    }

    private fun fillList() {
        myWorkList.add(
            MyWorksData(
                position = 0,
                image = R.drawable.ic_issues,
                background = R.color.green_for_issues,
                visible = true,
                workName = "Issues"
            )
        )
        myWorkList.add(
            MyWorksData(
                position = 1,
                image = R.drawable.ic_request,
                background = R.color.blue_for_full_request,
                visible = true,
                workName = "Pull Request"
            )
        )
        myWorkList.add(
            MyWorksData(
                position = 2,
                image = R.drawable.ic_chat,
                background = R.color.purple_for_discussions,
                visible = true,
                workName = "Discussions"
            )
        )
        myWorkList.add(
            MyWorksData(
                position = 3,
                image = R.drawable.ic_repository,
                background = R.color.grey_for_repositories,
                visible = true,
                workName = "Repositories"
            )
        )
        myWorkList.add(
            MyWorksData(
                position = 4,
                image = R.drawable.ic_organization,
                background = R.color.orange_for_organizations,
                visible = true,
                workName = "Organizations"
            )
        )
        myWorkList.add(
            MyWorksData(
                position = 5,
                image = R.drawable.ic_starred,
                background = R.color.yellow_for_starred,
                visible = true,
                workName = "Starred"
            )
        )
        adapter.submitList(myWorkList)
    }
}