package com.valorantwiki.valorantwikiapp

import com.valorantwiki.valorantwikiapp.domain.agent.entities.Agent

fun sampleAgent(uuid: String) = Agent(
    uuid = uuid,
 displayName= "Name $uuid",
description = "Description $uuid",
 displayIcon = "Icon",
 fullPortrait = "Portrait",
 background = "Background",
 backgroundGradientColors = listOf("Color1", "Color2", "Color3", "Color4"),
 isFavorite = false
)

fun sampleAgents(vararg uuids: String) = uuids.map { sampleAgent(it) }