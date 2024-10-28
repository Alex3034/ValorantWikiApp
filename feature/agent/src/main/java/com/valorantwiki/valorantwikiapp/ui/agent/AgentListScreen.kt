package com.valorantwiki.valorantwikiapp.ui.agent

import android.Manifest
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.valorantwiki.valorantwikiapp.ui.common.PermissionRequestEffect
import com.valorantwiki.valorantwikiapp.domain.agent.entities.Agent
import com.valorantwiki.valorantwikiapp.ui.common.AcScaffold
import com.valorantwiki.valorantwikiapp.ui.common.Screen
import com.valorantwiki.valorantwikiapp.ui.common.R as CommonR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentListScreen(onAgentClick: (Agent) -> Unit, vm: AgentViewModel) {

    val agentState = rememberAgentState()

    PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) {
        vm.onUiReady()
    }

    Screen {
        val state by vm.state.collectAsState()

        AcScaffold(
            state = state,
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = CommonR.string.app_name)) },
                    scrollBehavior = agentState.scrollBehavior
                )
            },
            modifier = Modifier.nestedScroll(agentState.scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing,
        ) { padding, agents ->

            LazyColumn(
                contentPadding = padding,
                modifier = Modifier.padding(horizontal = 4.dp)) {
                items(agents, key = { it.uuid }) {
                    AgentItem(
                        agent = it,
                        onAgentClick = { onAgentClick(it) }
                    )
                }
            }
        }
    }
}