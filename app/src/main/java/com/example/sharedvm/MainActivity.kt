package com.example.sharedvm

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sharedvm.databinding.ActivityMainBinding
import com.example.sharedvm.factory.SharedViewmodelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedViewModel = ViewModelProvider(
            this,
            SharedViewmodelFactory.getInstance()
        )[SharedViewModel::class.java]


        // Observe the shared data
        sharedViewModel.sharedData.observe(this) { data ->
            binding.count.text = data.toString()
        }

        binding.increment.setOnClickListener {
            sharedViewModel.onIncrementClick()
        }

        binding.decrement.setOnClickListener {
            sharedViewModel.onDecrementClick()
        }

        binding.details.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }


    }
}