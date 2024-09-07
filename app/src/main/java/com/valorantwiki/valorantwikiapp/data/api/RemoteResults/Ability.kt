package com.valorantwiki.valorantwikiapp.data.api.RemoteResults


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ability(
    @SerialName("description")
    val description: String,
    @SerialName("displayIcon")
    val displayIcon: String,
    @SerialName("displayName")
    val displayName: String,
    @SerialName("slot")
    val slot: String
)