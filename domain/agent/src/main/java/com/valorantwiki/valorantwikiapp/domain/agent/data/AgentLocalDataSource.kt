package com.valorantwiki.valorantwikiapp.domain.agent.data

import com.valorantwiki.valorantwikiapp.domain.agent.entities.Agent
import kotlinx.coroutines.flow.Flow

interface AgentLocalDataSource {
    val agents: Flow<List<Agent>>
    fun getAgentById(uuid: String): Flow<Agent?>

    suspend fun save(movies: List<Agent>)
}
