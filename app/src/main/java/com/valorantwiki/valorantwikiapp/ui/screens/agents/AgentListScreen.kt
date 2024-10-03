package com.valorantwiki.valorantwikiapp.ui.screens.agents

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.personalapps.mymoviedb.ui.common.PermissionRequestEffect
import com.valorantwiki.valorantwikiapp.R
import com.valorantwiki.valorantwikiapp.data.Agent
import com.valorantwiki.valorantwikiapp.ui.components.AgentItem
import com.valorantwiki.valorantwikiapp.ui.theme.ValorantWikiAppTheme
import kotlinx.coroutines.launch

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

    val coroutineScope = rememberCoroutineScope()

    PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) { granted ->
        coroutineScope.launch {
            vm.onUiReady()
        }
    }

    Screen {

        val state by vm.state.collectAsState()
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) },
                    scrollBehavior = scrollBehavior
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { padding ->
            if (state.loading) {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(padding)
                            .align(Alignment.Center)
                    )
                }
            }

            LazyColumn(modifier = Modifier.padding(padding)) {
                items(state.agent) { agent ->
                    AgentItem(
                        agent = agent,
                        onAgentClick = { onAgentClick(agent) }
                    )
                }
            }
        }
    }
}