package com.example.github.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.databinding.FragmentLoginBinding
import com.example.github.presentation.MainViewModel
import com.example.github.utils.getStringFromPref
import com.example.github.utils.logTag
import com.example.github.utils.putStringToPref
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private val mainViewModel: MainViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        initObservers()

        initListeners()

    }

    override fun onResume() {
        super.onResume()
        val uri: Uri? = requireActivity().intent?.data
        if (uri != null) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                Log.d("TTTT", "Code: $code")
                Toast.makeText(requireActivity(), "Login success: $code", Toast.LENGTH_SHORT).show()
                putStringToPref("code", code)

                MainScope().launch {
                    mainViewModel.getAccessToken()
                    if (getStringFromPref("access_key") != null) {
                        navigateToMainFragment()
                    } else {
                        Toast.makeText(requireActivity(), "Please try again!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            } else if (uri.getQueryParameter("error") != null) {
                Toast.makeText(requireActivity(), "Something went wrong", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initListeners() {
        binding.btnLoginWithEnterprise.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }

        binding.btnLogin.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW, Uri.parse(
                    "https://github.com/login/oauth/authorize?client_id=8f3cf5f09bd0c93a0528&scope=repo"
                )
            )
            startActivity(intent)
        }
    }

    private fun initObservers() {
        mainViewModel.getAccessTokenLiveData.observe(requireActivity()) {
            putStringToPref("access_key", it.toString())
        }

        mainViewModel.messageLiveData.observe(requireActivity()) {
            Toast.makeText(requireActivity(), "Message: $it", Toast.LENGTH_SHORT).show()
        }

        mainViewModel.errorLiveData.observe(requireActivity()) {
            it.printStackTrace()
        }
    }

    private fun navigateToMainFragment() {
        val accessKey = getStringFromPref("access_key")
        if (accessKey.isNullOrEmpty().not()) {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        } else {
            Log.d(logTag, "access_key null keldi, access_key: $accessKey")
            lifecycleScope.launch {
                mainViewModel.getAccessToken()
            }
        }
    }
}