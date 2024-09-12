package com.valorantwiki.valorantwikiapp.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.valorantwiki.valorantwikiapp.ui.screens.agents.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailAgentScreen(vm: DetailAgentViewModel, onBack: () -> Unit) {

    Screen {
        val state by vm.state.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(state.agent?.displayName ?: "") },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.Default.Close, contentDescription = "Volver")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                state.agent?.let { agent ->
                    AsyncImage(
                        model = agent.fullPortrait,
                        contentDescription = "Retrato de ${agent.displayName}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}