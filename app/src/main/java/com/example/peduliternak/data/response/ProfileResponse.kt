package com.example.peduliternak.data.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("users")
	val users: Users? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class Users(

	@field:SerializedName("noTelepon")
	val noTelepon: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
