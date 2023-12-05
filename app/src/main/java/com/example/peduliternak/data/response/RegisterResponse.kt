package com.example.peduliternak.data.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@SerializedName("message")
	val message: String? = null,

	@SerializedName("user")
	val user: User? = null,

	@SerializedName("status")
	val status: Boolean? = null,

	@SerializedName("token")
	val token: String? = null
)

data class User(

	@SerializedName("noTelepon")
	val noTelepon: String? = null,

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("username")
	val username: String? = null
)
