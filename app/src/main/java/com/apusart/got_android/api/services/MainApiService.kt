package com.apusart.got_android.api.services

import com.apusart.got_android.api.models.*
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

    @PUT("odcinki/{id}/czyAktywny/")
    suspend fun toggleSegment(
        @Path("id") id: Int,
        @Body toggleSegmentBody: ToggleSegmentBody
    ): Response<Segment>

    @Multipart
    @POST("odcinki/")
    suspend fun addSegment(
        @Part("nazwa") name: String,
        @Part("poczatek") start: Int,
        @Part("koniec") koniec: Int
    ): Response<Unit>

    @Multipart
    @POST("uczestnictwa/")
    suspend fun joinTrip(
        @Part("turysta") touristId: Int,
        @Part("wycieczka") tripId: Int
    ): Response<Unit>

    @GET("ksiazeczki/byowner/{id}/details/")
    suspend fun getBookByOwnerId(@Path("id") id: Int): Response<Book>

    @GET("profile/{id}/")
    suspend fun getUserProfileData(@Path("id") id: Int): Response<UserData>

    @GET("punkty/details")
    suspend fun getPoints(): Response<List<Point>>

    @GET("odznaki/manage/{id}/")
    suspend fun getManageBadgesData(@Path("id") id: Int): Response<ManageBadgesData>

    @Multipart
    @POST("odznaki/manage/{id}/")
    suspend fun addBadge(
        @Path("id") touristId: Int,
        @Part("wyPkt") reqPoints: Int,
        @Part("czyRozp") isExamined: Boolean,
        @Part("stopien") level: Int,
        @Part("rodzaj") type: Int,
        @Part("ksiazeczka") bookId: Int
    ): Response<ManageBadgesData>
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