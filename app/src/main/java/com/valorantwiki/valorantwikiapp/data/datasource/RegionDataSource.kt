package com.valorantwiki.valorantwikiapp.data.datasource

import android.app.Application
import android.location.Geocoder
import android.location.Location
import com.personalapps.mymoviedb.ui.common.getFromLocationCompat

const val DEFAULT_REGION = "US"

interface RegionDataSource {
    suspend fun findLastRegion(): String
}
