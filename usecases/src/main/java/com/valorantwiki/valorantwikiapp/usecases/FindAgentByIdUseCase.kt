package com.valorantwiki.valorantwikiapp.usecases

import com.valorantwiki.valorantwikiapp.domain.Agent
import com.valorantwiki.valorantwikiapp.data.AgentRepository
import kotlinx.coroutines.flow.Flow

class FindAgentByIdUseCase(
    private val agentRepository: AgentRepository
) {
    operator fun invoke(uuid: String): Flow<Agent> = agentRepository.findAgentById(uuid)
}