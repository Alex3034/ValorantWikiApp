package com.valorantwiki.valorantwikiapp.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantwiki.valorantwikiapp.data.AgentRepository
import com.valorantwiki.valorantwikiapp.data.model.Agent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailAgentViewModel(private val agentUuid: String) : ViewModel() {

    private var _state = MutableStateFlow(UiState())
    val state get(): StateFlow<UiState> = _state.asStateFlow()

    private val repository = AgentRepository()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(agent = repository.findAgentById(agentUuid))
        }
    }
}

data class UiState(
    val agent: Agent? = null,
    val loading: Boolean = false
)