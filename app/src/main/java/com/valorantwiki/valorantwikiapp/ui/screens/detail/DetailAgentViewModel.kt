package com.valorantwiki.valorantwikiapp.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantwiki.valorantwikiapp.Result
import com.valorantwiki.valorantwikiapp.data.Agent
import com.valorantwiki.valorantwikiapp.ifSuccess
import com.valorantwiki.valorantwikiapp.stateAsResultIn
import com.valorantwiki.valorantwikiapp.usecases.FindAgentByIdUseCase
import com.valorantwiki.valorantwikiapp.usecases.ToggleFavoriteUseCase
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

