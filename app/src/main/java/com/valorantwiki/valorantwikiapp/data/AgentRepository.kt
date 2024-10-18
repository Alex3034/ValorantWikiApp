package com.valorantwiki.valorantwikiapp.data

import com.valorantwiki.valorantwikiapp.data.datasource.AgentLocalDataSource
import com.valorantwiki.valorantwikiapp.data.datasource.AgentsRemoteDataSource
import com.valorantwiki.valorantwikiapp.domain.Agent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach

class AgentRepository(
    private val localDataSource: AgentLocalDataSource,
    private val remoteDataSource: AgentsRemoteDataSource
) {
    val agents: Flow<List<Agent>> = localDataSource.agents.onEach { localAgents ->
        if (localAgents.isEmpty()) {
            val remoteAgents = remoteDataSource.fetchAgents()
            localDataSource.save(remoteAgents)
        }
    }

    fun findAgentById(uuid: String): Flow<Agent> = localDataSource.getAgentById(uuid)
        .onEach { agent ->
            if (agent == null) {
                val remoteAgent = remoteDataSource.findAgentById(uuid)
                localDataSource.save(listOf(remoteAgent))
            }
        }
        .filterNotNull()

    suspend fun toggleFavorite(agent: Agent) {
        localDataSource.save(listOf(agent.copy(isFavorite = !agent.isFavorite)))
    }
}
