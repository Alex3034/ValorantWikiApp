package com.valorantwiki.valorantwikiapp.domain.agent.usecases

import com.valorantwiki.valorantwikiapp.domain.agent.data.AgentRepository
import com.valorantwiki.valorantwikiapp.domain.agent.entities.Agent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindAgentByIdUseCase @Inject constructor(
    private val agentRepository: AgentRepository
) {
    operator fun invoke(uuid: String): Flow<Agent> = agentRepository.findAgentById(uuid)
}