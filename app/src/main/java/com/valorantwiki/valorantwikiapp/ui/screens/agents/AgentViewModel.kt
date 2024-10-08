package com.valorantwiki.valorantwikiapp.ui.screens.agents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantwiki.valorantwikiapp.data.Agent
import com.valorantwiki.valorantwikiapp.data.AgentRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalCoroutinesApi::class)
class AgentViewModel(repository: AgentRepository) : ViewModel() {

    private val uiReady = MutableStateFlow(false)
    val state: StateFlow<UiState> = uiReady
            .filter { it }
            .flatMapLatest { repository.agents }
            .map { UiState(agent = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = UiState(loading = true)
            )

    fun onUiReady() {
        uiReady.value = true
    }

    data class UiState(
        val agent: List<Agent> = emptyList(),
        val loading: Boolean = false
    )
}