package com.dwiki.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.dwiki.myapplication.databinding.ActivitySecondBinding
import com.dwiki.myapplication.viewmodel.SecondViewModel

class SecondActivity : AppCompatActivity() {
    private var _binding: ActivitySecondBinding? = null
    private val binding get() = _binding!!

    private val secondViewModel : SecondViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val name = intent.getStringExtra("name") ?: "null"
        if (name != "null"){
            secondViewModel.setName(name)
        }

        secondViewModel.name.observe(this){
            binding.tvName.text = it
        }

        val selectedName = intent.getStringExtra("selected user") ?: "null"
        if(selectedName != "null"){
            binding.tvSelectedUserName.text = selectedName
        }

        binding.buttonBack.setOnClickListener {
            finish()
        }

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent (this, ThirdActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}