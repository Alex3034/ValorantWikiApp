package com.valorantwiki.valorantwikiapp.data

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

object RetrofitClient {

    private const val BASE_URL = "https://valorant-api.com/"
    private val json = Json { ignoreUnknownKeys = true }

    val instance: AgentsService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create<AgentsService>()
    }
}