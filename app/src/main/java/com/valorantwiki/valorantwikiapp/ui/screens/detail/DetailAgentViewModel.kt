package com.valorantwiki.valorantwikiapp.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantwiki.valorantwikiapp.Result
import com.valorantwiki.valorantwikiapp.data.AgentRepository
import com.valorantwiki.valorantwikiapp.data.Agent
import com.valorantwiki.valorantwikiapp.ifSuccess
import com.valorantwiki.valorantwikiapp.stateAsResultIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailAgentViewModel(private val repository: AgentRepository, agentUuid: String) : ViewModel() {

    val state: StateFlow<Result<Agent>> = repository.findAgentById(agentUuid)
        .stateAsResultIn(viewModelScope)

    fun onFavoriteClick() {
        state.value.ifSuccess {
            viewModelScope.launch {
                repository.toggleFavorite(it)
            }
        }
    }
}

