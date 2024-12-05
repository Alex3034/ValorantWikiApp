package com.valorantwiki.valorantwikiapp.domain.agent.usecases

import com.valorantwiki.valorantwikiapp.domain.agent.entities.Agent

fun sampleAgent(uuid: String) = Agent(
    uuid = uuid,
 displayName= "Name",
description = "Description",
 displayIcon = "Icon",
 fullPortrait = "Portrait",
 background = "Background",
 backgroundGradientColors = listOf("Color1", "Color2", "Color3", "Color4"),
 isFavorite = false
)

fun sampleAgents(vararg uuids: String) = uuids.map { sampleAgent(it) }