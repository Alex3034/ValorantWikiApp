package com.valorantwiki.valorantwikiapp.ui.utils

import androidx.compose.ui.graphics.Color

fun String.toColor(): Color = Color(this.toLong(16))