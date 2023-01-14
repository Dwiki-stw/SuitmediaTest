package com.dwiki.myapplication.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.dwiki.myapplication.R
import com.dwiki.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        binding.btnCheck.setOnClickListener {
            if (binding.inputText.text.isNotEmpty()){
                val result = isPolindrome(binding.inputText.text.toString())
                setupDialog(binding.inputText.text.toString(), result)
            }else{
                binding.inputText.error = error
            }
        }

        binding.btnNext.setOnClickListener {
            if (binding.inputName.text.isNotEmpty()){
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("name", binding.inputName.text.toString())
                startActivity(intent)
            }else{
                binding.inputName.error = error
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun isPolindrome(text: String):Boolean{
        val text2 = text.replace(" ", "")
        val result = text2.reversed()
        Log.d(TAG, result)

        return text2 == result
    }

    private fun setupDialog(text: String, condition: Boolean){

        val dialog = Dialog(this)

        dialog.setTitle(null)
        dialog.setCancelable(false)
        dialog.setOnCancelListener(null)

        dialog.setContentView(R.layout.dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val animation = dialog.findViewById<LottieAnimationView>(R.id.animationView)
        val word = dialog.findViewById<TextView>(R.id.text)
        val textResult = dialog.findViewById<TextView>(R.id.tvResult)
        val btnClose = dialog.findViewById<ImageButton>(R.id.btnClose)

        //setResult
       if (condition){
            textResult.text = "Is Polindrome"
            animation.setAnimation(R.raw.right)
        } else {
            textResult.text = "Not Polindrome"
            animation.setAnimation(R.raw.wrong)
            animation.speed = 2F
        }

        word.text = "\"$text\""

        dialog.show()

        btnClose.setOnClickListener {
            dialog.cancel()
        }
    }

    companion object{
        const val TAG = "MainActivity"
        const val error = "it's empty"
    }
}