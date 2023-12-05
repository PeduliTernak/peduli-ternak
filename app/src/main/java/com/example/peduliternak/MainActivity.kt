package com.example.peduliternak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.peduliternak.databinding.ActivityMainBinding
import com.example.peduliternak.databinding.ActivityRegisterBinding
import com.example.peduliternak.view.register.RegisterActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}