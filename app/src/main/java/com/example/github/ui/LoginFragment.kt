package com.example.github.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var pref: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        pref = requireActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE)

        val a = "d1be7641bd9ee1b14c20" //damir
        val b = "8f3cf5f09bd0c93a0528"
        binding.btnLogin.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW, Uri.parse(
                    "https://github.com/login/oauth/authorize?client_id=8f3cf5f09bd0c93a0528&scope=repo"
                )
            )
            startActivity(intent)
        }
        binding.btnLoginWithEnterprise.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        val uri: Uri? = requireActivity().intent?.data
        if (uri != null) {
            val code = uri.getQueryParameter("code")
            val accessKey = uri.getQueryParameter("access_key")
            Log.d("TTTT", "access_key: $accessKey")
            if (code != null) {
                Log.d("TTTT", "Code: $code")
//                Toast.makeText(requireActivity(), "Login success: $code", Toast.LENGTH_SHORT).show()
                pref.edit().putString("code", code).apply()
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            } else if (uri.getQueryParameter("error") != null) {
                Toast.makeText(requireActivity(), "Something went wrong", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show()
        }
        findNavController().popBackStack()
    }
}