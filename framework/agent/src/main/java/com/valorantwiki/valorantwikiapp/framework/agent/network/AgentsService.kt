package com.valorantwiki.valorantwikiapp.framework.agent.network

import com.valorantwiki.valorantwikiapp.framework.agent.network.remoteResults.AgentsResponse
import com.valorantwiki.valorantwikiapp.framework.agent.network.remoteResults.ApiResponse
import dagger.Provides
import retrofit2.http.GET
import retrofit2.http.Path

interface AgentsService {

    @GET("v1/agents")
    suspend fun getAgents(): AgentsResponse

    @GET("v1/agents/{agentUuid}")
    suspend fun getAgentById(@Path("agentUuid") uuid: String): ApiResponse
}
