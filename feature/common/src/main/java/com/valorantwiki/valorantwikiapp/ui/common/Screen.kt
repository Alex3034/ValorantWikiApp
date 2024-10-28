package com.valorantwiki.valorantwikiapp.ui.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.valorantwiki.valorantwikiapp.ui.common.theme.ValorantWikiAppTheme

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