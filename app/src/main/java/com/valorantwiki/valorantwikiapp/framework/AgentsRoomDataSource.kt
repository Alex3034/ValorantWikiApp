package com.valorantwiki.valorantwikiapp.framework

import com.valorantwiki.valorantwikiapp.data.datasource.AgentLocalDataSource
import com.valorantwiki.valorantwikiapp.domain.Agent
import com.valorantwiki.valorantwikiapp.framework.database.AgentDao
import com.valorantwiki.valorantwikiapp.framework.database.DbAgent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AgentsRoomDataSource(private val agentDao: AgentDao) : AgentLocalDataSource {
    override val agents: Flow<List<Agent>> =
        agentDao.getAllAgents().map { agents -> agents.map { it.toDomainAgent() } }

    override fun getAgentById(uuid: String): Flow<Agent?> =
        agentDao.getAgentById(uuid).map { it?.toDomainAgent() }

    override suspend fun save(movies: List<Agent>) = agentDao.save(movies.map { it.toDbAgent() })
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