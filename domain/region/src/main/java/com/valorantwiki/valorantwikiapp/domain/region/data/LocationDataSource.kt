package com.valorantwiki.valorantwikiapp.domain.region.data

import com.valorantwiki.valorantwikiapp.domain.region.entities.Location


interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}

