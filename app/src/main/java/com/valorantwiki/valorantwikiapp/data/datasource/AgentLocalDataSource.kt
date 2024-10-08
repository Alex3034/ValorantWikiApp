package com.valorantwiki.valorantwikiapp.data.datasource

import com.valorantwiki.valorantwikiapp.data.Agent
import com.valorantwiki.valorantwikiapp.data.datasource.database.AgentDao
import kotlinx.coroutines.flow.Flow

class AgentLocalDataSource(private val agentDao: AgentDao) {
    val agents: Flow<List<Agent>> = agentDao.getAllAgents()
    fun getAgentById(uuid: String): Flow<Agent?> = agentDao.getAgentById(uuid)
    suspend fun isEmpty(): Boolean = agentDao.countAgents() == 0
    suspend fun save(movies: List<Agent>) = agentDao.save(movies)
}