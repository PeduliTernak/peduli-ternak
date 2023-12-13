package com.example.peduliternak.view.fragment

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.peduliternak.data.UserRepository
import com.example.peduliternak.data.pref.UserModel
import com.example.peduliternak.data.response.HistoryResponse
import com.example.peduliternak.data.response.PredictionsItem
import com.example.peduliternak.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryViewModel(private val repository: UserRepository) : ViewModel() {

    private val _predict = MutableLiveData<List<PredictionsItem>>()
//    val predict: LiveData<List<PredictionsItem>> = _predict

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun getPredict(token: String) : LiveData<List<PredictionsItem>> {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getHistory(token)
        client.enqueue(object : Callback<HistoryResponse> {
            override fun onResponse(
                call: Call<HistoryResponse>,
                response: Response<HistoryResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _predict.value = response.body()?.predictions as List<PredictionsItem>?
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return _predict
    }
}