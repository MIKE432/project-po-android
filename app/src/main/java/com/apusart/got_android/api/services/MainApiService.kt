package com.apusart.got_android.api.services

import com.apusart.got_android.api.models.Segment
import com.apusart.got_android.api.models.SegmentRequestBody
import com.apusart.got_android.api.models.ToggleSegmentBody
import com.apusart.got_android.api.models.Trip
import com.apusart.got_android.api.tools.Defaults
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface MainApiService {
    @GET("odcinki/details")
    suspend fun getSegments(): Response<List<Segment>>

    @GET("odcinki/{id}/details")
    suspend fun getSegmentById(@Path("id") id: Int): Response<Segment>


    @GET("wycieczki/details")
    suspend fun getTrips(): Response<List<Trip>>

    @GET("wycieczki/{id}/details")
    suspend fun getTrip(@Path("id") id: Int): Response<Trip>

    @PUT("odcinki/{id}/czyAktywny")
    suspend fun toggleSegment(@Path("id") id: Int, @Body toggleSegmentBody: ToggleSegmentBody): Response<Segment>

    @Multipart
    @POST("odcinki/")
    suspend fun addSegment(@Part("nazwa") name: String, @Part("poczatek") start: Int, @Part("koniec") koniec: Int): Response<Unit>

    @Multipart
    @POST("uczestnictwa/")
    suspend fun joinTrip(@Part("turysta") touristId: Int, @Part("wycieczka") tripId: Int): Response<Unit>
}

object RetrofitImpl {
    val retrofit: MainApiService

    init {
        val gsonCorverterFactory = GsonConverterFactory.create()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client =
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

        retrofit =
            Retrofit.Builder()
                .baseUrl(Defaults.baseUrl)
                .addConverterFactory(gsonCorverterFactory)
                .client(client)
                .build()
                .create(MainApiService::class.java)
    }
}