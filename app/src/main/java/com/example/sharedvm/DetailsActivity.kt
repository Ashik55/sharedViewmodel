package com.example.sharedvm

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sharedvm.databinding.ActivityDetailsBinding
import com.example.sharedvm.factory.SharedViewmodelFactory

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
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


    }
}