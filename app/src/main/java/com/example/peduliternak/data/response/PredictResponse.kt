package com.example.peduliternak.data.response

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("prediction")
	val prediction: Prediction? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class Result(

	@field:SerializedName("penyakit")
	val penyakit: List<String?>? = null,

	@field:SerializedName("penanganan")
	val penanganan: List<String?>? = null
)

data class Prediction(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
