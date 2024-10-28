package com.valorantwiki.valorantwikiapp.domain.agent.usecases

import com.valorantwiki.valorantwikiapp.domain.agent.data.AgentRepository
import com.valorantwiki.valorantwikiapp.domain.agent.entities.Agent
import kotlinx.coroutines.flow.Flow

class AgentsUseCase(private val repository: AgentRepository) {
    operator fun invoke(): Flow<List<Agent>> = repository.agents
}