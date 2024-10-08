package com.valorantwiki.valorantwikiapp.data

import com.valorantwiki.valorantwikiapp.data.datasource.AgentLocalDataSource
import com.valorantwiki.valorantwikiapp.data.datasource.AgentsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform

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

    fun findAgentById(uuid: String): Flow<Agent?> =
        localDataSource.getAgentById(uuid).onEach {
            if (it == null) {
                val remoteAgent = remoteDataSource.findAgentById(uuid)
                localDataSource.save(listOf(remoteAgent))
            }
        }
}
