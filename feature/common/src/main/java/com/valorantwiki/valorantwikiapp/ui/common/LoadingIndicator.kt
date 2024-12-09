package com.valorantwiki.valorantwikiapp.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

const val LOADING_INDICATOR_TAG = "loading_indicator"

@Composable
fun LoadingIndicator(padding: PaddingValues, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag(LOADING_INDICATOR_TAG),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = modifier.padding(padding))
    }
}