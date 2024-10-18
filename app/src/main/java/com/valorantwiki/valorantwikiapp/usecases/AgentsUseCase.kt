package com.valorantwiki.valorantwikiapp.usecases

import com.valorantwiki.valorantwikiapp.data.Agent
import com.valorantwiki.valorantwikiapp.data.AgentRepository
import kotlinx.coroutines.flow.Flow

class AgentsUseCase(private val repository: AgentRepository) {
    operator fun invoke(): Flow<List<Agent>> = repository.agents
}