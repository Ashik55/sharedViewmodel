package com.example.sharedvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val sharedData = MutableLiveData(0)

    fun onIncrementClick() {
        sharedData.value = sharedData.value?.plus(1)
    }

    fun onDecrementClick() {
        sharedData.value = sharedData.value?.minus(1)
    }


    //Factory
    companion object {
        private var instance: SharedViewModel? = null
        fun getInstance() =
            instance ?: synchronized(SharedViewModel::class.java) {
                instance ?: SharedViewModel().also { instance = it }
            }
    }
}