package com.valorantwiki.valorantwikiapp.domain.region.data

import com.valorantwiki.valorantwikiapp.data.datasource.RegionDataSource
import javax.inject.Inject

class RegionRepository @Inject constructor(private val regionDataSource: RegionDataSource) {

    suspend fun findLastRegion(): String = regionDataSource.findLastRegion()

}
