package com.valorantwiki.valorantwikiapp.ui.agent

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.valorantwiki.valorantwikiapp.sampleAgents
import com.valorantwiki.valorantwikiapp.ui.common.LOADING_INDICATOR_TAG
import com.valorantwiki.valorantwikiapp.ui.common.Result
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class AgentListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun whenLoadingState_showProgress(): Unit = with(composeTestRule) {
        setContent {
            AgentListScreen(
                state = Result.Loading,
                onAgentClick = {}
            )
        }
        onNodeWithTag(LOADING_INDICATOR_TAG).assertExists()
    }
    @Test
    fun whenErrorState_showError(): Unit = with(composeTestRule) {
        setContent {
            AgentListScreen(
                state = Result.Error(RuntimeException("An error occurred")),
                onAgentClick = {}
            )
        }
        onNodeWithText("An error occurred").assertExists()
    }
    @Test
    fun whenSuccessState_agentsAreShown(): Unit = with(composeTestRule) {
        setContent {
            AgentListScreen(
                state = Result.Success(sampleAgents("1", "2", "3")),
                onAgentClick = {}
            )
        }
        onNodeWithText("Name 2").assertExists()
    }
    @Test
    fun whenAgentClicked_listenerIsCalled(): Unit = with(composeTestRule) {
        var clickedAgentUuid = "-1"
        val agents = sampleAgents("1", "2", "3")
        setContent {
            AgentListScreen(
                state = Result.Success(agents),
                onAgentClick = { clickedAgentUuid = it.uuid }
            )
        }
        onNodeWithText("Name 2").performClick()
        assertEquals("2", clickedAgentUuid)
    }
}