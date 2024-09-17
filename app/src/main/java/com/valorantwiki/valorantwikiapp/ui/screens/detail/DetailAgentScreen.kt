package com.valorantwiki.valorantwikiapp.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.valorantwiki.valorantwikiapp.R
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
                            Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    shape = MaterialTheme.shapes.extraLarge,
                    onClick = { /*TODO*/ } ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = stringResource(id = R.string.add_to_favorite)
                    )
                }
            },

        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                state.agent?.let { agent ->
                    Box(modifier = Modifier.fillMaxSize()){
                        AsyncImage(
                            model = agent.background,
                            contentDescription = "Background de ${agent.displayName}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(500.dp)
                                .align(Alignment.Center)
                                .background(color = Color.Black),
                            contentScale = ContentScale.FillHeight,
                            colorFilter = ColorFilter.tint(Color.Cyan)
                        )
                        AsyncImage(
                            model = agent.fullPortrait,
                            contentDescription = "Retrato de ${agent.displayName}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(300.dp)
                                .align(Alignment.Center),
                            contentScale = ContentScale.FillHeight
                        )
                    }
                    Text(
                        text = agent.description,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
                    )
                }
            }
        }
    }
}