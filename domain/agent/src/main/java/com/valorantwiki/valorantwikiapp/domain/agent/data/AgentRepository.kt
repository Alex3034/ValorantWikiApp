package com.valorantwiki.valorantwikiapp.domain.agent.data

import com.valorantwiki.valorantwikiapp.domain.agent.entities.Agent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach

interface IAgentRepository {
    val agents: Flow<List<Agent>>
    fun findAgentById(uuid: String): Flow<Agent>

    suspend fun toggleFavorite(agent: Agent)
}

class AgentRepository(
    private val localDataSource: AgentLocalDataSource,
    private val remoteDataSource: AgentsRemoteDataSource
) : IAgentRepository {
    override val agents: Flow<List<Agent>> = localDataSource.agents.onEach { localAgents ->
        if (localAgents.isEmpty()) {
            val remoteAgents = remoteDataSource.fetchAgents()
            localDataSource.save(remoteAgents)
        }
    }

    override fun findAgentById(uuid: String): Flow<Agent> = localDataSource.getAgentById(uuid)
        .onEach { agent ->
            if (agent == null) {
                val remoteAgent = remoteDataSource.findAgentById(uuid)
                localDataSource.save(listOf(remoteAgent))
            }
        }
        .filterNotNull()

    override suspend fun toggleFavorite(agent: Agent) {
        localDataSource.save(listOf(agent.copy(isFavorite = !agent.isFavorite)))
    }
}
