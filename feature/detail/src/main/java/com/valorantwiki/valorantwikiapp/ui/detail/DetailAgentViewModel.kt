package com.valorantwiki.valorantwikiapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantwiki.valorantwikiapp.ui.common.Result
import com.valorantwiki.valorantwikiapp.domain.agent.entities.Agent
import com.valorantwiki.valorantwikiapp.ui.common.ifSuccess
import com.valorantwiki.valorantwikiapp.ui.common.stateAsResultIn
import com.valorantwiki.valorantwikiapp.domain.agent.usecases.FindAgentByIdUseCase
import com.valorantwiki.valorantwikiapp.domain.agent.usecases.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailAgentViewModel(
    agentUuid: String,
    findAgentByIdUseCase: FindAgentByIdUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    val state: StateFlow<Result<Agent>> = findAgentByIdUseCase(agentUuid)
        .stateAsResultIn(viewModelScope)

    fun onFavoriteClick() {
        state.value.ifSuccess {
            viewModelScope.launch {
                toggleFavoriteUseCase(it)
            }
        }
    }
}

