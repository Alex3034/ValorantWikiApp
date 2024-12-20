package com.valorantwiki.valorantwikiapp.ui.detail

import app.cash.turbine.test
import com.valorantwiki.valorantwikiapp.domain.agent.usecases.FindAgentByIdUseCase
import com.valorantwiki.valorantwikiapp.domain.agent.usecases.ToggleFavoriteUseCase
import com.valorantwiki.valorantwikiapp.sampleAgent
import com.valorantwiki.valorantwikiapp.testrules.CoroutinesTestRule
import com.valorantwiki.valorantwikiapp.ui.common.Result
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class DetailAgentViewModelTest  {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var findAgentByIdUseCase: FindAgentByIdUseCase

    @Mock
    lateinit var toggleFavoriteUseCase: ToggleFavoriteUseCase

    private lateinit var vm: DetailAgentViewModel

    private val agent = sampleAgent("2")

    @Before
    fun setUp() {
        whenever(findAgentByIdUseCase("2")).thenReturn(flowOf(agent))
        vm = DetailAgentViewModel("2", findAgentByIdUseCase, toggleFavoriteUseCase)
    }

    @Test
    fun `UI is updated with the movie on start`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(agent), awaitItem())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Favorite action calls the corresponding use case`() = runTest(coroutinesTestRule.testDispatcher) {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(agent), awaitItem())

            vm.onFavoriteClick()
            runCurrent()

            verify(toggleFavoriteUseCase).invoke(any())
        }
    }
}