package com.valorantwiki.valorantwikiapp.data

import com.valorantwiki.valorantwikiapp.data.api.RemoteResults.AgentData
import com.valorantwiki.valorantwikiapp.data.api.RetrofitClient
import com.valorantwiki.valorantwikiapp.data.model.Agent

class AgentRepository {

    suspend fun fetchAgents(): List<Agent> =
        RetrofitClient
            .instance
            .getAgents()
            .data
            .map { it.toDomainModel() }
}

private fun AgentData.toDomainModel(): Agent =
    Agent(
        uuid = uuid,
        displayName = displayName,
        description = description,
        displayIcon = displayIcon,
        fullPortrait = fullPortrait,
        role = role?.toDomainModel(),
        abilities = abilities.map { it.toDomainModel() }

    )