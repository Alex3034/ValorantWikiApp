package com.valorantwiki.valorantwikiapp.ui.detail

import app.cash.turbine.test
import com.valorantwiki.valorantwikiapp.buildAgentsRepositoryWith
import com.valorantwiki.valorantwikiapp.domain.agent.usecases.FindAgentByIdUseCase
import com.valorantwiki.valorantwikiapp.domain.agent.usecases.ToggleFavoriteUseCase
import com.valorantwiki.valorantwikiapp.sampleAgent
import com.valorantwiki.valorantwikiapp.sampleAgents
import com.valorantwiki.valorantwikiapp.testrules.CoroutinesTestRule
import com.valorantwiki.valorantwikiapp.ui.common.Result
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailAgentViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var vm: DetailAgentViewModel

    @Before
    fun setUp() {
        val agentsRepository = buildAgentsRepositoryWith(localData = sampleAgents("1", "2"))
        vm = DetailAgentViewModel(
            "2",
            FindAgentByIdUseCase(agentsRepository),
            ToggleFavoriteUseCase(agentsRepository)
        )
    }
    @Test
    fun `UI is updated with the agent on start`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(sampleAgent("2")), awaitItem())
        }
    }
    @Test
    fun `Favorite is updated in local data source`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(sampleAgent("2")), awaitItem())
            vm.onFavoriteClick()
            runCurrent()
            assertEquals(Result.Success(sampleAgent("2").copy(isFavorite = true)), awaitItem())
        }
    }
}