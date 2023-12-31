package com.example.peduliternak.data.retrofit

import com.example.peduliternak.data.pref.LoginData
import com.example.peduliternak.data.pref.UserData
import com.example.peduliternak.data.response.HistoryResponse
import com.example.peduliternak.data.response.PredictResponse
import com.example.peduliternak.data.response.ProfileResponse
import com.example.peduliternak.data.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("register")
    suspend fun register(
        @Body userData: UserData
    ): RegisterResponse


    @POST("login")
    suspend fun login(
//        @Field("phone") email: String,
//        @Field("password") password: String
        @Body loginData: LoginData
    ): RegisterResponse

    @GET("/api/prediction")
    fun getHistory(
        @Header("Authorization") token: String,
    ): Call<HistoryResponse>

    @GET("/api/user")
    fun getUser(
        @Header("Authorization") token: String,
    ): Call<ProfileResponse>

    @Multipart
    @POST("/api/prediction")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part,
        @Part("gejala_matrix") description: RequestBody,
    ): PredictResponse
//
//    @GET("stories")
//    fun getStories(
//        @Header("Authorization") token: String,
//        @Query("page") page: Int = 1,
//        @Query("size") size: Int = 20
//    ): Call<StoryResponse>
//
//    @GET("stories/{id}")
//    fun getDetailStories(
//        @Header("Authorization") token: String,
//        @Path("id") id: String
//    ): Call<DetailResponse>
//
//    @Multipart
//    @POST("stories")
//    fun uploadImage(
//        @Header("Authorization") token: String,
//        @Part file: MultipartBody.Part,
//        @Part("description") description: RequestBody,
//        @Part("lat") lat: RequestBody?,
//        @Part("lon") lon: RequestBody?
//    ): Call<FileUploadResponse>
//
//    @GET("stories")
//    fun getStoriesWithLocation(
//        @Header("Authorization") token: String,
//        @Query("location") location : Int = 1
//    ): Call<StoryResponse>
}