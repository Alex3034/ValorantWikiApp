package com.valorantwiki.valorantwikiapp.data

import com.valorantwiki.valorantwikiapp.data.RemoteResults.RemoteAgent
import com.valorantwiki.valorantwikiapp.data.RemoteResults.AgentsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AgentsService {

    @GET("v1/agents")
    suspend fun getAgents(): AgentsResponse

    @GET("v1/agents/{agentUuid}")
    suspend fun getAgentById(@Path("agentUuid") uuid: String): RemoteAgent
}
