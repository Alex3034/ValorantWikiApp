package com.valorantwiki.valorantwikiapp.ui.screens.agents

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantwiki.valorantwikiapp.data.AgentRepository
import com.valorantwiki.valorantwikiapp.data.RetrofitClient
import com.valorantwiki.valorantwikiapp.data.model.Agent
import com.valorantwiki.valorantwikiapp.ui.screens.detail.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AgentViewModel : ViewModel() {

    private var _state = MutableStateFlow(UiState())
    val state get(): StateFlow<UiState> = _state.asStateFlow()

    private val repository = AgentRepository()

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(loading = false, agent = repository.fetchAgents())
        }
    }

    data class UiState(
        val agent: List<Agent> = emptyList(),
        val loading: Boolean = false
    )
}