package com.valorantwiki.valorantwikiapp.ui.agent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantwiki.valorantwikiapp.ui.common.stateAsResultIn
import com.valorantwiki.valorantwikiapp.domain.agent.entities.Agent
import com.valorantwiki.valorantwikiapp.domain.agent.usecases.AgentsUseCase
import com.valorantwiki.valorantwikiapp.ui.common.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class AgentViewModel(
    private val agentsUseCase: AgentsUseCase
) : ViewModel() {

    private val uiReady = MutableStateFlow(false)

    val state: StateFlow<Result<List<Agent>>> = uiReady
        .filter { it }
        .flatMapLatest { agentsUseCase() }
        .stateAsResultIn(viewModelScope)


    fun onUiReady() {
        uiReady.value = true
    }
}