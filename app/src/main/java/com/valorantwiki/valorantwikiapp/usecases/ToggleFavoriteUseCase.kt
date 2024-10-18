package com.valorantwiki.valorantwikiapp.usecases

import com.valorantwiki.valorantwikiapp.data.Agent
import com.valorantwiki.valorantwikiapp.data.AgentRepository

class ToggleFavoriteUseCase(private val agentRepository: AgentRepository) {
    suspend operator fun invoke(agent: Agent) {
        agentRepository.toggleFavorite(agent)
    }
}