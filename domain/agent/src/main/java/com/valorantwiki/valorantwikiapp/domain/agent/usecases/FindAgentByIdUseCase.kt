package com.valorantwiki.valorantwikiapp.domain.agent.usecases

import com.valorantwiki.valorantwikiapp.domain.agent.data.AgentRepository
import com.valorantwiki.valorantwikiapp.domain.agent.entities.Agent
import kotlinx.coroutines.flow.Flow

class FindAgentByIdUseCase(
    private val agentRepository: AgentRepository
) {
    operator fun invoke(uuid: String): Flow<Agent> = agentRepository.findAgentById(uuid)
}