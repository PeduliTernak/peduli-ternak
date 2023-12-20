package com.example.peduliternak.data.response

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

	@field:SerializedName("predictions")
	val predictions: List<PredictionsItem?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class Results(

	@field:SerializedName("penyakit")
	val penyakit: List<String?>? = null,

	@field:SerializedName("penanganan")
	val penanganan: List<String?>? = null
)

data class PredictionsItem(

	@field:SerializedName("result")
	val result: Results? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
