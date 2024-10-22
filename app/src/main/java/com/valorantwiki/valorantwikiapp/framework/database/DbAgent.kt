package com.valorantwiki.valorantwikiapp.framework.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbAgent (
        @PrimaryKey
        val uuid: String,
        val displayName: String,
        val description: String,
        val displayIcon: String,
        val fullPortrait: String?,
        val background: String?,
        val backgroundGradientColors: List<String>,
        val isFavorite: Boolean
)