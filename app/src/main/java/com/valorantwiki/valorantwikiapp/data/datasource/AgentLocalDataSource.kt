package com.valorantwiki.valorantwikiapp.data.datasource

import com.valorantwiki.valorantwikiapp.data.Agent
import com.valorantwiki.valorantwikiapp.data.datasource.database.AgentDao

class AgentLocalDataSource(private val agentDao: AgentDao) {
    suspend fun getAllAgents(): List<Agent> = agentDao.getAllAgents()
    suspend fun getAgentById(uuid: String): Agent? = agentDao.getAgentById(uuid)
    suspend fun isEmpty(): Boolean = agentDao.countAgents() == 0
    suspend fun save(movies: List<Agent>) = agentDao.save(movies)
}