package com.valorantwiki.valorantwikiapp.framework.agent.database

import com.valorantwiki.valorantwikiapp.domain.agent.data.AgentLocalDataSource
import com.valorantwiki.valorantwikiapp.domain.agent.entities.Agent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class AgentsRoomDataSource @Inject constructor(private val agentDao: AgentDao) : AgentLocalDataSource {
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