package com.valorantwiki.valorantwikiapp.data.RemoteResults


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val status: Int,
    val data: RemoteAgent
)


@Serializable
data class RemoteAgent(
    @SerialName("uuid") val uuid: String,
    @SerialName("displayName") val displayName: String,
    @SerialName("description") val description: String,
    @SerialName("displayIcon") val displayIcon: String,
    @SerialName("fullPortrait") val fullPortrait: String? = null,
    @SerialName("background") val background: String? = null,

    /*
    @SerialName("abilities")
    val abilities: List<Ability>,
    @SerialName("assetPath")
    val assetPath: String,
    @SerialName("backgroundGradientColors")
    val backgroundGradientColors: List<String>,
    @SerialName("bustPortrait")
    val bustPortrait: String,
    @SerialName("characterTags")
    val characterTags: List<String>,
    @SerialName("developerName")
    val developerName: String,
    @SerialName("displayIconSmall")
    val displayIconSmall: String,
    @SerialName("fullPortraitV2")
    val fullPortraitV2: String,
    @SerialName("isAvailableForTest")
    val isAvailableForTest: Boolean,
    @SerialName("isBaseContent")
    val isBaseContent: Boolean,
    @SerialName("isFullPortraitRightFacing")
    val isFullPortraitRightFacing: Boolean,
    @SerialName("isPlayableCharacter")
    val isPlayableCharacter: Boolean,
    @SerialName("killfeedPortrait")
    val killfeedPortrait: String,
    @SerialName("recruitmentData")
    val recruitmentData: RecruitmentData,
    @SerialName("role")
    val role: Role,
     */
)