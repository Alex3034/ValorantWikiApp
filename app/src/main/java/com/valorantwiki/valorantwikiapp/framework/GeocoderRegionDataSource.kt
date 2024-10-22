package com.valorantwiki.valorantwikiapp.framework

import android.location.Geocoder
import android.location.Location
import com.personalapps.mymoviedb.ui.common.getFromLocationCompat
import com.valorantwiki.valorantwikiapp.data.datasource.DEFAULT_REGION
import com.valorantwiki.valorantwikiapp.data.datasource.LocationDataSource
import com.valorantwiki.valorantwikiapp.data.datasource.RegionDataSource

class GeocoderRegionDataSource(
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