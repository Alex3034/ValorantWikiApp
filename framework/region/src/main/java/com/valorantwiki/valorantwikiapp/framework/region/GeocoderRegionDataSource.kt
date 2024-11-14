package com.valorantwiki.valorantwikiapp.framework.region

import android.location.Geocoder
import com.valorantwiki.valorantwikiapp.data.datasource.DEFAULT_REGION
import com.valorantwiki.valorantwikiapp.domain.region.data.LocationDataSource
import com.valorantwiki.valorantwikiapp.data.datasource.RegionDataSource
import com.valorantwiki.valorantwikiapp.domain.region.entities.Location
import javax.inject.Inject

internal class GeocoderRegionDataSource @Inject constructor(
    private val geocoder: Geocoder,
    private val locationDataSource: LocationDataSource
) : RegionDataSource {

    override suspend fun findLastRegion(): String = locationDataSource.findLastLocation()?.toRegion() ?: DEFAULT_REGION

    private suspend fun Location.toRegion(): String {
        val addresses = geocoder.getFromLocationCompat(latitude, longitude, 1)
        val region = addresses.firstOrNull()?.countryCode
        return region ?: DEFAULT_REGION
    }
}