package com.example.peduliternak.view.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.peduliternak.data.UserRepository
import com.example.peduliternak.data.pref.UserModel

class ResultViewModel(private val repository: UserRepository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}