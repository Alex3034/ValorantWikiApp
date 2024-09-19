package com.valorantwiki.valorantwikiapp.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import com.valorantwiki.valorantwikiapp.R
import com.valorantwiki.valorantwikiapp.ui.screens.agents.Screen
import com.valorantwiki.valorantwikiapp.ui.utils.toColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailAgentScreen(vm: DetailAgentViewModel, onBack: () -> Unit) {

    val state by vm.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.message) {
        state.message?.let {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(it)
            vm.onMessageShown()
        }
    }

    Screen {
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
                    onClick = { vm.onFavoriteClick() }) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = stringResource(id = R.string.add_to_favorite)
                    )
                }
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState)},
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
            ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                state.agent?.let { agent ->
                    val colors = agent.backgroundGradientColors.map { it.toColor() }

                    Box(modifier = Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = agent.background,
                            contentDescription = "Background de ${agent.displayName}",
                            contentScale = ContentScale.FillHeight,
                            //colorFilter = ColorFilter.tint(Color.Cyan)
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(500.dp)
                                .align(Alignment.Center)
                                .drawBehind {
                                    drawRect(Color.Black)
                                    drawRect(
                                        brush = Brush.linearGradient(
                                            colors = colors,
                                            tileMode = TileMode.Clamp
                                        )
                                    )
                                },
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
