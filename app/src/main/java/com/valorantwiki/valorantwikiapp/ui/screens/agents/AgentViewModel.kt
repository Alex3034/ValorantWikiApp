package com.valorantwiki.valorantwikiapp.ui.screens.agents

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valorantwiki.valorantwikiapp.data.api.RetrofitClient
import com.valorantwiki.valorantwikiapp.data.model.Agent
import kotlinx.coroutines.launch

class AgentViewModel : ViewModel() {

    val agents = mutableStateOf<List<Agent>>(emptyList())
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    init {
        fetchAgents()
    }

    private fun fetchAgents() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = RetrofitClient.instance.getAgents()
                agents.value = response.data
                error.value = null
            } catch (e: Exception) {
                error.value = "Error al cargar los agentes: ${e.message}"
            }
            isLoading.value = false
        }
    }

    fun getAgentById(agentId: String?): Agent? {
        return agents.value.find { it.uuid == agentId }
    }
}