package com.valorantwiki.valorantwikiapp.data.datasource

import com.valorantwiki.valorantwikiapp.data.datasource.database.AgentDao
import com.valorantwiki.valorantwikiapp.data.datasource.database.DbAgent
import com.valorantwiki.valorantwikiapp.domain.Agent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AgentLocalDataSource(private val agentDao: AgentDao) {
    val agents: Flow<List<Agent>> =
        agentDao.getAllAgents().map { agents -> agents.map { it.toDomainAgent() } }

    fun getAgentById(uuid: String): Flow<Agent?> =
        agentDao.getAgentById(uuid).map { it?.toDomainAgent() }

    suspend fun isEmpty(): Boolean = agentDao.countAgents() == 0
    suspend fun save(movies: List<Agent>) = agentDao.save(movies.map { it.toDbAgent() })
}

private fun DbAgent.toDomainAgent() = Agent(
    uuid = uuid,
    displayName = displayName,
    description = description,
    displayIcon = displayIcon,
    fullPortrait = fullPortrait,
    background = background,
    backgroundGradientColors = backgroundGradientColors,
    isFavorite = isFavorite
)

private fun Agent.toDbAgent() = DbAgent(
    uuid = uuid,
    displayName = displayName,
    description = description,
    displayIcon = displayIcon,
    fullPortrait = fullPortrait,
    background = background,
    backgroundGradientColors = backgroundGradientColors,
    isFavorite = isFavorite
)