package com.valorantwiki.valorantwikiapp.domain.agent.usecases

import com.valorantwiki.valorantwikiapp.sampleAgents
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class AgentsUseCaseTest {

    @Test
    fun `Invoke calls repository`() {
        val agentFlow = flowOf(sampleAgents("1","2"))
        val useCase = AgentsUseCase(mock{
            on { agents } doReturn agentFlow
        })

        val result = useCase()

        assertEquals(agentFlow, result)
    }

}
