package com.valorantwiki.valorantwikiapp.domain.agent.usecases

import com.valorantwiki.valorantwikiapp.domain.agent.data.AgentRepository
import com.valorantwiki.valorantwikiapp.sampleAgent
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ToggleFavoriteUseCaseTest{
    @Test
    fun `Invoke calls repository`(): Unit = runBlocking {
        val agent = sampleAgent("1")
        val repository = mock<AgentRepository>()
        val useCase = ToggleFavoriteUseCase(repository)

        useCase(agent)

        verify(repository).toggleFavorite(agent)
    }
}