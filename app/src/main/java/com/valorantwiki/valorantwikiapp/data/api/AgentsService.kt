package com.valorantwiki.valorantwikiapp.data.api

import com.valorantwiki.valorantwikiapp.data.model.Agent
import retrofit2.http.GET

interface AgentsService {
    @GET("v1/agents")
    suspend fun getAgents(): AgentResponse
}

data class AgentResponse(
    val status: Int,
    val data: List<Agent>
)