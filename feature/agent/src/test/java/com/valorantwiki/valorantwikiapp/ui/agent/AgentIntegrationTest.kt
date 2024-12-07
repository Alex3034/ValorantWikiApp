package com.valorantwiki.valorantwikiapp.ui.agent

import app.cash.turbine.test
import com.valorantwiki.valorantwikiapp.buildAgentsRepositoryWith
import com.valorantwiki.valorantwikiapp.domain.agent.entities.Agent
import com.valorantwiki.valorantwikiapp.domain.agent.usecases.AgentsUseCase
import com.valorantwiki.valorantwikiapp.sampleAgents
import com.valorantwiki.valorantwikiapp.testrules.CoroutinesTestRule
import com.valorantwiki.valorantwikiapp.ui.common.Result
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test


class AgentIntegrationTest {

@get:Rule
val coroutinesTestRule = CoroutinesTestRule()

@Test
fun `data is loaded from server when local source is empty`() = runTest {
    val remoteData = sampleAgents("1", "2")
    val vm = buildViewModelWith(
        localData = emptyList(),
        remoteData = remoteData
    )
    vm.onUiReady()
    vm.state.test {
        assertEquals(Result.Loading, awaitItem())
        assertEquals(Result.Success(emptyList<Agent>()), awaitItem())
        assertEquals(Result.Success(remoteData), awaitItem())
    }
}
@Test
fun `data is loaded from local source when available`() = runTest {
    val localData = sampleAgents("1", "2")
    val vm = buildViewModelWith(localData = localData)
    vm.onUiReady()
    vm.state.test {
        assertEquals(Result.Loading, awaitItem())
        assertEquals(Result.Success(localData), awaitItem())
    }
}
}
private fun buildViewModelWith(
    localData: List<Agent> = emptyList(),
    remoteData: List<Agent> = emptyList()
): AgentViewModel {
    val agentsUseCase = AgentsUseCase(buildAgentsRepositoryWith(localData, remoteData))
    return AgentViewModel(agentsUseCase)
}