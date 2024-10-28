package com.valorantwiki.valorantwikiapp.ui.common

import androidx.compose.ui.graphics.Color

fun String.toColor(): Color = Color(this.toLong(16))