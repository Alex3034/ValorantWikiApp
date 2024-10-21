package com.valorantwiki.valorantwikiapp.data.datasource

import com.valorantwiki.valorantwikiapp.data.datasource.remote.AgentsService
import com.valorantwiki.valorantwikiapp.data.datasource.remote.remoteResults.RemoteAgent
import com.valorantwiki.valorantwikiapp.data.datasource.remote.RetrofitClient
import com.valorantwiki.valorantwikiapp.domain.Agent

interface AgentsRemoteDataSource {
    suspend fun fetchAgents(): List<Agent>

    suspend fun findAgentById(id: String): Agent
}

class AgentsServerDataSource(
    private val agentsService: AgentsService
) : AgentsRemoteDataSource {

    override suspend fun fetchAgents(): List<Agent> =
        agentsService.getAgents()
            .data
            .map { it.toDomainModel() }

    override suspend fun findAgentById(id: String): Agent =
        agentsService.getAgentById(id)
            .data
            .toDomainModel()
}

fun RemoteAgent.toDomainModel(): Agent =
    Agent(
        uuid = uuid,
        displayName = displayName,
        description = description,
        displayIcon = displayIcon,
        fullPortrait = fullPortrait,
        background = background,
        backgroundGradientColors = backgroundGradientColors,
        isFavorite = false
    )