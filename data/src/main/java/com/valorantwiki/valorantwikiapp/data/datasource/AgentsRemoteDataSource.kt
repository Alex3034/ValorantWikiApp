package com.valorantwiki.valorantwikiapp.data.datasource

import com.valorantwiki.valorantwikiapp.domain.Agent

interface AgentsRemoteDataSource {
    suspend fun fetchAgents(): List<Agent>

    suspend fun findAgentById(id: String): Agent
}

