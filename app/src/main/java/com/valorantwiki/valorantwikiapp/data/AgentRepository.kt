package com.valorantwiki.valorantwikiapp.data

import com.valorantwiki.valorantwikiapp.data.datasource.AgentLocalDataSource
import com.valorantwiki.valorantwikiapp.data.datasource.AgentsRemoteDataSource

class AgentRepository(
    private val localDataSource: AgentLocalDataSource,
    private val agentsRemoteDataSource: AgentsRemoteDataSource
) {

    suspend fun fetchAgents(): List<Agent> {
        if (localDataSource.isEmpty()) {
            val agents = agentsRemoteDataSource.fetchAgents()
            localDataSource.save(agents)
        }
        return localDataSource.getAllAgents()
    }

    suspend fun findAgentById(uuid: String): Agent {
        if (localDataSource.getAgentById(uuid) == null) {
            val agent = agentsRemoteDataSource.findAgentById(uuid)
            localDataSource.save(listOf(agent))
        }
        return checkNotNull(localDataSource.getAgentById(uuid))
    }
}
