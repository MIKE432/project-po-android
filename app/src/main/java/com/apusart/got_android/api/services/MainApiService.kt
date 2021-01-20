package com.apusart.got_android.api.services

import com.apusart.got_android.api.tools.Defaults
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface MainApiService {
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