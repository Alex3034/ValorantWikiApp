package com.valorantwiki.valorantwikiapp.framework

import com.valorantwiki.valorantwikiapp.data.datasource.AgentsRemoteDataSource
import com.valorantwiki.valorantwikiapp.domain.Agent
import com.valorantwiki.valorantwikiapp.framework.remote.AgentsService
import com.valorantwiki.valorantwikiapp.framework.remote.remoteResults.RemoteAgent

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