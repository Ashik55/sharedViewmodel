package com.example.sharedvm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sharedvm.SharedViewModel

class SharedViewmodelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(SharedViewModel::class.java) -> SharedViewModel.getInstance()
                else -> throw IllegalArgumentException("Unknown viewModel class $modelClass")
            }
        } as T

    companion object {
        private var instance: SharedViewmodelFactory? = null
        fun getInstance() =
            instance ?: synchronized(SharedViewmodelFactory::class.java) {
                instance ?: SharedViewmodelFactory().also { instance = it }
            }
    }
}