package com.valorantwiki.valorantwikiapp.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import coil.compose.AsyncImage
import com.valorantwiki.valorantwikiapp.domain.agent.entities.Agent
import com.valorantwiki.valorantwikiapp.ui.common.AcScaffold
import com.valorantwiki.valorantwikiapp.ui.common.Screen
import com.valorantwiki.valorantwikiapp.ui.common.toColor
import com.valorantwiki.valorantwikiapp.ui.common.R as CommonR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailAgentScreen(vm: DetailAgentViewModel, onBack: () -> Unit) {

    val state by vm.state.collectAsState()
    val detailState = rememberDetailState(state)

    Screen {
        AcScaffold(
            state = state,
            topBar = {
                TopAppBar(
                    title = { Text(detailState.topBarTitle) },
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
                    containerColor = Color.DarkGray,
                    onClick = { vm.onFavoriteClick() }) {
                    val favorite = detailState.agent?.isFavorite ?: false
                    Icon(
                        imageVector = if (favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        tint = if (favorite) Color.Red else Color.White,
                        contentDescription = stringResource(id = CommonR.string.add_to_favorite)
                    )
                }
            },
            snackbarHost = { SnackbarHost(hostState = detailState.snackbarHostState) },
            modifier = Modifier.nestedScroll(detailState.scrollBehavior.nestedScrollConnection)
        ) { padding, agent ->
            DetailAgentScreen(
                agent = agent,
                padding = padding
            )
        }
    }
}

@Composable
private fun DetailAgentScreen(
    agent: Agent,
    padding: PaddingValues
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(padding)
    ) {
        val colors = agent.backgroundGradientColors.map { it.toColor() }

        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = agent.background,
                contentDescription = "Background de ${agent.displayName}",
                contentScale = ContentScale.FillHeight,
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
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(300.dp)
                    .align(Alignment.Center)
            )
        }
        Text(
            text = agent.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
        )

    }
}
