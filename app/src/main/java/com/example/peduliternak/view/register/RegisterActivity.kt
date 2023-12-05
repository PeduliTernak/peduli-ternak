package com.example.peduliternak.view.register

import android.content.ContentValues.TAG
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.peduliternak.R
import com.example.peduliternak.data.pref.UserData
import com.example.peduliternak.data.response.RegisterErrorResponse
import com.example.peduliternak.data.response.RegisterResponse
import com.example.peduliternak.data.retrofit.ApiConfig
import com.example.peduliternak.databinding.ActivityRegisterBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.registerButton.setOnClickListener {
            showLoading(true)
            val noTelepon = binding.phoneEditText.text.toString()
            val name = binding.nameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val username = name

            lifecycleScope.launch {
                try {
                    Log.d(TAG, "setupAction: success")
                    val apiService = ApiConfig.getApiService()
                    val userData = UserData(username, name, noTelepon, password)
                    val successResponse = apiService.register(userData).message
                    showToast(successResponse)
                    showLoading(false)
                    AlertDialog.Builder(this@RegisterActivity).apply {
                        setTitle("Yeah!")
                        setMessage("Yuk, login")
                        setPositiveButton(getString(R.string.next)) { _, _ ->
                            finish()
                        }
                        create()
                        show()
                    }

                } catch (e: HttpException) {

//                    val errorBody = e.response()?.errorBody().toString()
//                    val errorResponse = Gson().fromJson(errorBody, RegisterErrorResponse::class.java)
//                    showToast(errorResponse.error)
                    when (e.code()) {
                        400 -> {
                            showToast("register gagal : invalid input")
                        }
                        409 -> {
                            showToast("register gagal : username sudah terpakai")
                        }
                        else -> {
                            showToast("register gagal : unknown error")
                        }
                    }

                    showLoading(false)
                }
            }
        }
    }

    private fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}