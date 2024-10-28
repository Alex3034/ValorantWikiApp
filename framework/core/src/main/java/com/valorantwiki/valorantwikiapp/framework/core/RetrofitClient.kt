package com.valorantwiki.valorantwikiapp.framework.core

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

object RetrofitClient {

    private const val BASE_URL = "https://valorant-api.com/"
    private val json = Json { ignoreUnknownKeys = true; coerceInputValues = true }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val instance: com.valorantwiki.valorantwikiapp.framework.agent.network.AgentsService by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create<com.valorantwiki.valorantwikiapp.framework.agent.network.AgentsService>()
    }
}