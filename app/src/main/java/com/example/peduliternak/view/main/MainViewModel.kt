package com.example.peduliternak.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.peduliternak.data.UserRepository
import com.example.peduliternak.data.pref.UserModel
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository) : ViewModel() {

//    fun getStories(token: String): LiveData<PagingData<ListStoryItem>> =
//        repository.getStories(token).cachedIn(viewModelScope)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

}