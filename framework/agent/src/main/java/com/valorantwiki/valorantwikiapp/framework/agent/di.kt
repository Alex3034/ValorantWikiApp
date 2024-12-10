package com.valorantwiki.valorantwikiapp.framework.agent

import com.valorantwiki.valorantwikiapp.domain.agent.data.AgentLocalDataSource
import com.valorantwiki.valorantwikiapp.domain.agent.data.AgentsRemoteDataSource
import com.valorantwiki.valorantwikiapp.framework.agent.database.AgentsRoomDataSource
import com.valorantwiki.valorantwikiapp.framework.agent.network.AgentsServerDataSource
import com.valorantwiki.valorantwikiapp.framework.agent.network.AgentsService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FrameworkAgentModule {
    @Binds
    abstract fun bindLocalDataSource(localDataSource: AgentsRoomDataSource): AgentLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: AgentsServerDataSource): AgentsRemoteDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitClientModule {

    private const val BASE_URL = "https://valorant-api.com/"
    private val json = Json { ignoreUnknownKeys = true; coerceInputValues = true }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    fun provideAgentService() : AgentsService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create<AgentsService>()
    }
}