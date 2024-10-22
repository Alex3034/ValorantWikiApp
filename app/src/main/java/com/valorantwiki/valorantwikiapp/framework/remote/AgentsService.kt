package com.valorantwiki.valorantwikiapp.framework.remote

import com.valorantwiki.valorantwikiapp.framework.remote.remoteResults.AgentsResponse
import com.valorantwiki.valorantwikiapp.framework.remote.remoteResults.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AgentsService {

    @GET("v1/agents")
    suspend fun getAgents(): AgentsResponse

    @GET("v1/agents/{agentUuid}")
    suspend fun getAgentById(@Path("agentUuid") uuid: String): ApiResponse
}
