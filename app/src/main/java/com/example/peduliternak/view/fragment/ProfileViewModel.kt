package com.example.peduliternak.view.fragment

import android.content.ContentValues
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
import com.example.peduliternak.data.response.ProfileResponse
import com.example.peduliternak.data.response.Users
import com.example.peduliternak.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {

    private val _predict = MutableLiveData<Users>()
//    val predict: LiveData<List<PredictionsItem>> = _predict

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

    fun getUser(token: String) : LiveData<Users> {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUser(token)
        client.enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _predict.value = response.body()?.users as Users
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return _predict
    }
}