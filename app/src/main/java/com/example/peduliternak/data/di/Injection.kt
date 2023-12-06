package com.example.peduliternak.data.di

import android.content.Context
import com.example.peduliternak.data.UserRepository
import com.example.peduliternak.data.pref.UserPreference
import com.example.peduliternak.data.pref.dataStore

object Injection {

    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }
}