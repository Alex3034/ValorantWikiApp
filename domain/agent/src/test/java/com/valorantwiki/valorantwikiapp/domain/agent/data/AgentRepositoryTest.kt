package com.valorantwiki.valorantwikiapp.domain.agent.data

import com.valorantwiki.valorantwikiapp.sampleAgent
import com.valorantwiki.valorantwikiapp.sampleAgents
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argThat
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class AgentRepositoryTest {

    @Mock
    private lateinit var localDataSource: AgentLocalDataSource

    @Mock
    private lateinit var remoteDataSource: AgentsRemoteDataSource

    private lateinit var repository: AgentRepository

    private val localAgents = sampleAgents("1", "2")
    private val remoteAgents = sampleAgents("3", "4")

    @Before
    fun setUp() {
        repository = AgentRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun `Agents are taken from local data source if available`() = runBlocking {
        whenever(localDataSource.agents).thenReturn(flowOf(localAgents))

        val result = repository.agents

        assertEquals(localAgents, result.first())

    }

    @Test
    fun `Agents are saved to local data source when it is empty`() = runBlocking {
        whenever(localDataSource.agents).thenReturn(flowOf(emptyList()))
        whenever(remoteDataSource.fetchAgents()).thenReturn(remoteAgents)

        repository.agents.first()

        verify(localDataSource).save(remoteAgents)
    }

    @Test
    fun `Finding a movie by id is done in local data source`(): Unit = runBlocking {
        val agent = sampleAgent("5")
        whenever(localDataSource.getAgentById("5")).thenReturn(flowOf(agent))

        val result = repository.findAgentById("5")

        assertEquals(agent, result.first())
    }

    @Test
    fun `Toggling favorite updates local data source`(): Unit = runBlocking {
        val agent = sampleAgent("3")
        repository.toggleFavorite(agent)

        verify(localDataSource).save(argThat { get(0).uuid == "3" })
    }

    @Test
    fun `Switching favorite marks as favorite an unfavorite agent`(): Unit = runBlocking {
        val agent = sampleAgent("1").copy(isFavorite = false)
        repository.toggleFavorite(agent)

        verify(localDataSource).save(argThat { get(0).isFavorite })
    }

    @Test
    fun `Switching favorite marks as unfavorite a favorite agent`(): Unit = runBlocking {
        val agent = sampleAgent("1").copy(isFavorite = true)
        repository.toggleFavorite(agent)

        verify(localDataSource).save(argThat { !get(0).isFavorite })
    }
}