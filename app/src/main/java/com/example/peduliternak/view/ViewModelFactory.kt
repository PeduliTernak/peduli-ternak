package com.example.peduliternak.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.peduliternak.data.UserRepository
import com.example.peduliternak.data.di.Injection
import com.example.peduliternak.view.fragment.CameraViewModel
import com.example.peduliternak.view.fragment.HistoryViewModel
import com.example.peduliternak.view.fragment.PredictViewModel
import com.example.peduliternak.view.fragment.ProfileViewModel
import com.example.peduliternak.view.fragment.ResultViewModel
import com.example.peduliternak.view.login.LoginViewModel
import com.example.peduliternak.view.main.MainViewModel

class ViewModelFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }
//            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
//                DetailViewModel(repository) as T
//            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(repository) as T
            }
            modelClass.isAssignableFrom(PredictViewModel::class.java) -> {
                PredictViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CameraViewModel::class.java) -> {
                CameraViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ResultViewModel::class.java) -> {
                ResultViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(repository) as T
            }
//            modelClass.isAssignableFrom(AddStoryViewModel::class.java) -> {
//                AddStoryViewModel(repository) as T
//            }
//            modelClass.isAssignableFrom(MapsViewModel::class.java) -> {
//                MapsViewModel(repository) as T
//            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}