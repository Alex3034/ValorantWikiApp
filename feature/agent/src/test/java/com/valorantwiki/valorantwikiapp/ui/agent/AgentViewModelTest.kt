package com.valorantwiki.valorantwikiapp.ui.agent

import app.cash.turbine.test
import com.valorantwiki.valorantwikiapp.domain.agent.usecases.AgentsUseCase
import com.valorantwiki.valorantwikiapp.sampleAgents
import com.valorantwiki.valorantwikiapp.testrules.CoroutinesTestRule
import com.valorantwiki.valorantwikiapp.ui.common.Result
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class AgentViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var agentsUseCase: AgentsUseCase

    private lateinit var vm: AgentViewModel

    @Before
    fun setUp() {
        vm = AgentViewModel(agentsUseCase)
    }

    @Test
    fun `Agents are not requested if UI is not ready`() = runTest {
        vm.state.first()
        runCurrent()
        verify(agentsUseCase, times(0)).invoke()
    }
    @Test
    fun `Agents are requested if UI is ready`() = runTest {
        val agents = sampleAgents("1", "2", "3")
        whenever(agentsUseCase()).thenReturn(flowOf(agents))
        vm.onUiReady()
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(agents), awaitItem())
        }
    }
    @Test
    fun `Error is propagated when request fails`() = runTest {
        val error = RuntimeException("Boom!")
        whenever(agentsUseCase()).thenThrow(error)
        vm.onUiReady()
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            val exceptionMessage = (awaitItem() as Result.Error).exception.message
            assertEquals("Boom!", exceptionMessage)
        }
    }
}