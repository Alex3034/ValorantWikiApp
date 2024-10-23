package com.valorantwiki.valorantwikiapp.data.datasource

import com.valorantwiki.valorantwikiapp.domain.Agent
import kotlinx.coroutines.flow.Flow

interface AgentLocalDataSource {
    val agents: Flow<List<Agent>>
    fun getAgentById(uuid: String): Flow<Agent?>

    suspend fun save(movies: List<Agent>)
}
