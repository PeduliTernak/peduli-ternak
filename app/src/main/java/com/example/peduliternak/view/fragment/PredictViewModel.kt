package com.example.peduliternak.view.fragment

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.peduliternak.data.UserRepository
import com.example.peduliternak.data.pref.UserModel
import com.example.peduliternak.data.response.HistoryResponse
import com.example.peduliternak.data.response.PredictionsItem
import com.example.peduliternak.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PredictViewModel(private val repository: UserRepository) : ViewModel() {

    private val _predict = MutableLiveData<List<PredictionsItem>>()
//    val predict: LiveData<List<PredictionsItem>> = _predict

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }


}