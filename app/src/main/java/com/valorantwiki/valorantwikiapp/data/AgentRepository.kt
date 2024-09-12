package com.valorantwiki.valorantwikiapp.data

import com.valorantwiki.valorantwikiapp.data.RemoteResults.RemoteAgent
import com.valorantwiki.valorantwikiapp.data.model.Agent

class AgentRepository {

    suspend fun fetchAgents(): List<Agent> =
        RetrofitClient
            .instance
            .getAgents()
            .data
            .map { it.toDomainModel() }

    suspend fun findAgentById(id: String): Agent =
        RetrofitClient
            .instance
            .getAgentById(id)
            .toDomainModel()
}

private fun RemoteAgent.toDomainModel(): Agent =
    Agent(
        uuid = uuid,
        displayName = displayName,
        description = description,
        displayIcon = displayIcon,
        fullPortrait = fullPortrait,
    )