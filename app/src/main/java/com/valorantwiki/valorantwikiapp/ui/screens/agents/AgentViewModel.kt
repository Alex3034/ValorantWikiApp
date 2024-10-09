package com.valorantwiki.valorantwikiapp.ui.screens.agents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantwiki.valorantwikiapp.Result
import com.valorantwiki.valorantwikiapp.data.Agent
import com.valorantwiki.valorantwikiapp.data.AgentRepository
import com.valorantwiki.valorantwikiapp.stateAsResultIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class AgentViewModel(repository: AgentRepository) : ViewModel() {

    private val uiReady = MutableStateFlow(false)

    val state: StateFlow<Result<List<Agent>>> = uiReady
            .filter { it }
            .flatMapLatest { repository.agents }
            .stateAsResultIn(viewModelScope)


    fun onUiReady() {
        uiReady.value = true
    }
}