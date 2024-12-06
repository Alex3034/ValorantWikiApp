package com.valorantwiki.valorantwikiapp.domain.agent.usecases

import com.valorantwiki.valorantwikiapp.sampleAgent
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class FindAgentByIdUseCaseTest {
    @Test
    fun `Invoke calls repository`() {
        val agentFlow = flowOf(sampleAgent("1"))
        val useCase = FindAgentByIdUseCase(mock {
            on { findAgentById("1") } doReturn agentFlow
        })

        val result = useCase("1")

        assertEquals(agentFlow, result)
    }
}