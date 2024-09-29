package com.valorantwiki.valorantwikiapp.data

import com.valorantwiki.valorantwikiapp.data.datasource.AgentsRemoteDataSource
import com.valorantwiki.valorantwikiapp.data.model.Agent

class AgentRepository(
    private val agentsRemoteDataSource: AgentsRemoteDataSource
) {

    suspend fun fetchAgents(): List<Agent> = agentsRemoteDataSource.fetchAgents()

    suspend fun findAgentById(id: String): Agent = agentsRemoteDataSource.findAgentById(id)
}
