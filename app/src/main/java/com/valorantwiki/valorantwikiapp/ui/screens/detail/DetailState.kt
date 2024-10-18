package com.valorantwiki.valorantwikiapp.ui.screens.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.valorantwiki.valorantwikiapp.Result
import com.valorantwiki.valorantwikiapp.domain.Agent

@OptIn(ExperimentalMaterial3Api::class)
class DetailState(
    private val state: Result<Agent>,
    val scrollBehavior: TopAppBarScrollBehavior,
    val snackbarHostState: SnackbarHostState
){
    val agent: Agent?
        get() = (state as? Result.Success)?.data
    val topBarTitle: String
        get() = agent?.displayName ?: ""
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberDetailState(
    state: Result<Agent>,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) = remember(state) { DetailState(state, scrollBehavior, snackbarHostState) }