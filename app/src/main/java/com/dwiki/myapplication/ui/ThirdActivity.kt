package com.dwiki.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwiki.myapplication.adapter.LoadingStateAdapter
import com.dwiki.myapplication.adapter.PagingAdapter
import com.dwiki.myapplication.databinding.ActivityThirdBinding
import com.dwiki.myapplication.helper.ViewModelFactory
import com.dwiki.myapplication.viewmodel.ThirdViewModel

class ThirdActivity : AppCompatActivity() {

    private var _binding: ActivityThirdBinding? = null
    private val binding get() = _binding!!

    private val thirdViewModel: ThirdViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.buttonBack.setOnClickListener {
            finish()
        }

        setupListUser()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupListUser(){
        val adapter = PagingAdapter()
        binding.listUsers.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter()

        )
        binding.listUsers.layoutManager = LinearLayoutManager(this)
        thirdViewModel.listUser.observe(this){
            adapter.submitData(lifecycle, it)
        }
    }
}