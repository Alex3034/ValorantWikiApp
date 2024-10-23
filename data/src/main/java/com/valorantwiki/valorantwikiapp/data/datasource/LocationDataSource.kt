package com.valorantwiki.valorantwikiapp.data.datasource

import com.valorantwiki.valorantwikiapp.domain.Location


interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}

