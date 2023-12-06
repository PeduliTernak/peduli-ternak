package com.example.peduliternak.data.pref

data class UserModel(
    val phone: String,
    val token: String,
    val isLogin: Boolean = false
)