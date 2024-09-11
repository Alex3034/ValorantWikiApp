package com.valorantwiki.valorantwikiapp.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantwiki.valorantwikiapp.data.AgentRepository
import com.valorantwiki.valorantwikiapp.data.model.Agent
import kotlinx.coroutines.launch

class DetailAgentViewModel(private val agentUuid: Int) : ViewModel() {

    var state by mutableStateOf(UiState())

    private val repository = AgentRepository()

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(agent = repository.findAgentById(agentUuid))
        }
    }
}

data class UiState(
    val agent: Agent? = null,
    val loading: Boolean = false
)