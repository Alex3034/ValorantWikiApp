package com.valorantwiki.valorantwikiapp.data.api

import com.valorantwiki.valorantwikiapp.data.api.RemoteResults.AgentData
import com.valorantwiki.valorantwikiapp.data.api.RemoteResults.AgentsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AgentsService {

    @GET("v1/agents")
    suspend fun getAgents(): AgentsResponse

    @GET("v1/agents/{agentUuid}")
    suspend fun getAgentById(@Path("uuid") agentId: String): AgentData
}
