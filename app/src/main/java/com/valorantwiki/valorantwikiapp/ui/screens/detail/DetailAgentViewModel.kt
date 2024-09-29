package com.valorantwiki.valorantwikiapp.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantwiki.valorantwikiapp.data.AgentRepository
import com.valorantwiki.valorantwikiapp.data.model.Agent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailAgentViewModel(
    private val agentUuid: String,
    private val repository: AgentRepository
) : ViewModel() {

    private var _state = MutableStateFlow(UiState())
    val state get(): StateFlow<UiState> = _state.asStateFlow()

    data class UiState(
        val agent: Agent? = null,
        val loading: Boolean = false,
        val message: String? = null
    )

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(agent = repository.findAgentById(agentUuid))
        }
    }

    fun onFavoriteClick() {
        _state.update { it.copy(message = "Agregado a favoritos") }
    }

    fun onMessageShown() {
        _state.update { it.copy(message = null) }
    }
}

