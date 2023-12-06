package com.example.peduliternak.view.login

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.peduliternak.view.main.MainActivity
import com.example.peduliternak.R
import com.example.peduliternak.data.pref.LoginData
import com.example.peduliternak.data.pref.UserModel
import com.example.peduliternak.data.response.RegisterResponse
import com.example.peduliternak.data.retrofit.ApiConfig
import com.example.peduliternak.databinding.ActivityLoginBinding
import com.example.peduliternak.view.ViewModelFactory
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
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
        binding.loginButton.setOnClickListener {
            showLoading(true)
            val phone = binding.phoneEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            lifecycleScope.launch {
                try {
                    val apiService = ApiConfig.getApiService()
                    val loginData = LoginData(phone, password)
                    val successResponse = apiService.login(loginData).message
                    val token = apiService.login(loginData).token
                    showToast(successResponse)

                    viewModel.saveSession(UserModel(phone, token.toString()))
                    showLoading(false)
                    AlertDialog.Builder(this@LoginActivity).apply {
                        setTitle("Yeah!")
                        setMessage(getString(R.string.success_login))
                        setPositiveButton(getString(R.string.next)) { _, _ ->
                            val intent = Intent(context, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                            finish()
                        }
                        create()
                        show()
                    }

                } catch (e: HttpException) {
                    val errorBody = e.response()?.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
                    showToast(errorResponse.message)
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