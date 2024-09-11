package com.valorantwiki.valorantwikiapp.ui.screens.agents

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantwiki.valorantwikiapp.data.AgentRepository
import com.valorantwiki.valorantwikiapp.data.RetrofitClient
import com.valorantwiki.valorantwikiapp.data.model.Agent
import kotlinx.coroutines.launch

class AgentViewModel : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    private val repository = AgentRepository()

    fun onUiReady() {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(loading = false, agent = repository.fetchAgents())
        }
    }

    data class UiState(
        val agent: List<Agent> = emptyList(),
        val loading: Boolean = false
    )
}