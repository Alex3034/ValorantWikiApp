package com.valorantwiki.valorantwikiapp.framework

import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import com.valorantwiki.valorantwikiapp.data.datasource.LocationDataSource
import com.valorantwiki.valorantwikiapp.domain.Location
import android.location.Location as AndroidLocation
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class PlayServicesLocationDataSource(
    private val fusedLocationClient: FusedLocationProviderClient
) : LocationDataSource {
    override suspend fun findLastLocation(): Location? = fusedLocationClient.lastLocation()
}

@SuppressLint("MissingPermission")
private suspend fun FusedLocationProviderClient.lastLocation(): Location? {
    return suspendCancellableCoroutine { continuation ->
        lastLocation.addOnSuccessListener { location ->
            continuation.resume(location.toDomainLocation())
        }.addOnFailureListener {
            continuation.resume(null)
        }
    }
}

private fun AndroidLocation.toDomainLocation(): Location = Location(latitude, longitude)
