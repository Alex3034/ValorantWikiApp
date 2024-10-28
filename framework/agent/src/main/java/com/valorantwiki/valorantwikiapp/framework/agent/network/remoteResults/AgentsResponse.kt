package com.valorantwiki.valorantwikiapp.framework.agent.network.remoteResults


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AgentsResponse(
    @SerialName("data")
    val `data`: List<RemoteAgent>,
    @SerialName("status")
    val status: Int
)

/*
@Serializable
data class RecruitmentData(
    @SerialName("counterId")
    val counterId: String,
    @SerialName("endDate")
    val endDate: String,
    @SerialName("levelVpCostOverride")
    val levelVpCostOverride: Int,
    @SerialName("milestoneId")
    val milestoneId: String,
    @SerialName("milestoneThreshold")
    val milestoneThreshold: Int,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("useLevelVpCostOverride")
    val useLevelVpCostOverride: Boolean
)

 */