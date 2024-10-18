package com.valorantwiki.valorantwikiapp.ui.screens.agents

import android.Manifest
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.personalapps.mymoviedb.ui.common.PermissionRequestEffect
import com.valorantwiki.valorantwikiapp.R
import com.valorantwiki.valorantwikiapp.domain.Agent
import com.valorantwiki.valorantwikiapp.ui.common.AcScaffold
import com.valorantwiki.valorantwikiapp.ui.components.AgentItem
import com.valorantwiki.valorantwikiapp.ui.theme.ValorantWikiAppTheme

@Composable
fun Screen(content: @Composable () -> Unit) {
    ValorantWikiAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentListScreen(onAgentClick: (Agent) -> Unit, vm: AgentViewModel = viewModel()) {

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
                    title = { Text(text = stringResource(id = R.string.app_name)) },
                    scrollBehavior = agentState.scrollBehavior
                )
            },
            modifier = Modifier.nestedScroll(agentState.scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing,
        ) { padding, agents ->

            LazyColumn(modifier = Modifier.padding(padding)) {
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