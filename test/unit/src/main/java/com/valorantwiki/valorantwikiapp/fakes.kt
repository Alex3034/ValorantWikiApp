package com.valorantwiki.valorantwikiapp

import com.valorantwiki.valorantwikiapp.domain.agent.data.AgentLocalDataSource
import com.valorantwiki.valorantwikiapp.domain.agent.data.AgentRepository
import com.valorantwiki.valorantwikiapp.domain.agent.data.AgentsRemoteDataSource
import com.valorantwiki.valorantwikiapp.domain.agent.entities.Agent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

fun buildAgentsRepositoryWith(
    localData: List<Agent> = emptyList(),
    remoteData: List<Agent> = emptyList()
): AgentRepository {
    val localDataSource = FakeLocalDataSource().apply { inMemoryAgents.value = localData }
    val remoteDataSource = FakeRemoteDataSource().apply { agents = remoteData }
    return AgentRepository( localDataSource, remoteDataSource)
}

class FakeLocalDataSource : AgentLocalDataSource {

    val inMemoryAgents = MutableStateFlow<List<Agent>>(emptyList())

    override val agents = inMemoryAgents

    override fun getAgentById(uuid: String): Flow<Agent?> =
        inMemoryAgents.map { it.firstOrNull { agent -> agent.uuid == uuid } }

    override suspend fun save(agents: List<Agent>) {
        inMemoryAgents.value = agents
    }
}

class FakeRemoteDataSource : AgentsRemoteDataSource {

    var agents = sampleAgents("1", "2", "3", "4")

    override suspend fun fetchAgents() = agents

    override suspend fun findAgentById(uuid: String): Agent = agents.first { it.uuid == uuid }
}
