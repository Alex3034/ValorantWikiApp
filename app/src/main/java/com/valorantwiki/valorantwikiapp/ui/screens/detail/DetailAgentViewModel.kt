package com.valorantwiki.valorantwikiapp.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantwiki.valorantwikiapp.data.AgentRepository
import com.valorantwiki.valorantwikiapp.data.Agent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailAgentViewModel(repository: AgentRepository, agentUuid: String) : ViewModel() {

    private val message = MutableStateFlow<String?>(null)
    val state: StateFlow<UiState> =
        combine(repository.findAgentById(agentUuid), message) { agent, message ->
            UiState(
                loading = false,
                agent = agent,
                message = message
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState(loading = true)
        )

    data class UiState(
        val agent: Agent? = null,
        val loading: Boolean = false,
        val message: String? = null
    )

    fun onFavoriteClick() {
        message.value = "Favorite added"
    }

    fun onMessageShown() {
        message.value = null
    }
}

