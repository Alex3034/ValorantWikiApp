package com.valorantwiki.valorantwikiapp.ui.screens.agents

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.valorantwiki.valorantwikiapp.R
import com.valorantwiki.valorantwikiapp.data.model.Agent
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
fun AgentListScreen(onAgentClick: (Agent) -> Unit, viewModel: AgentViewModel = viewModel()) {

    Screen {

        val agents = viewModel.agents.value
        val isLoading = viewModel.isLoading.value
        val error = viewModel.error.value

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
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.padding(padding))
                }

                error != null -> {
                    Text(text = error, modifier = Modifier.padding(padding))
                }

                else -> {
                    LazyColumn(modifier = Modifier.padding(padding)) {
                        items(agents) { agent ->
                            AgentItem(
                                agent = agent,
                                onAgentClick = { onAgentClick(agent) }
                            )
                        }
                    }
                }
            }

        }
    }
}