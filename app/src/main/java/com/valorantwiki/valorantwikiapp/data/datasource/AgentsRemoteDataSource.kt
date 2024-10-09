package com.valorantwiki.valorantwikiapp.data.datasource

import com.valorantwiki.valorantwikiapp.data.datasource.remote.remoteResults.RemoteAgent
import com.valorantwiki.valorantwikiapp.data.datasource.remote.RetrofitClient
import com.valorantwiki.valorantwikiapp.data.Agent

class AgentsRemoteDataSource {

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