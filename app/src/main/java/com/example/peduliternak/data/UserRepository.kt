package com.example.peduliternak.data

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.peduliternak.data.pref.UserModel
import com.example.peduliternak.data.pref.UserPreference
import kotlinx.coroutines.flow.Flow

class UserRepository private constructor(
    private val userPreference: UserPreference
) {

//    fun getStories(token: String): LiveData<PagingData<ListStoryItem>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = 20
//            ),
//            pagingSourceFactory = {
//                Log.d(ContentValues.TAG, "tokenrepository: $token")
//                PagingSource(token)
//            }
//        ).liveData
//    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference)
            }.also { instance = it }
    }
}