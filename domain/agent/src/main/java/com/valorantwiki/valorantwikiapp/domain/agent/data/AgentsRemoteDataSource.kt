package com.valorantwiki.valorantwikiapp.domain.agent.data

import com.valorantwiki.valorantwikiapp.domain.agent.entities.Agent

interface AgentsRemoteDataSource {
    suspend fun fetchAgents(): List<Agent>

    suspend fun findAgentById(uuid: String): Agent
}

