package com.valorantwiki.valorantwikiapp.data

import com.valorantwiki.valorantwikiapp.data.datasource.RegionDataSource

class RegionRepository(private val regionDataSource: RegionDataSource) {

    suspend fun findLastRegion(): String = regionDataSource.findLastRegion()

}
