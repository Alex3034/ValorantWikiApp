package com.valorantwiki.valorantwikiapp.framework.region

import android.app.Application
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import com.valorantwiki.valorantwikiapp.data.datasource.RegionDataSource
import com.valorantwiki.valorantwikiapp.domain.region.data.LocationDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FrameworkRegionModule {
    @Provides
    fun provideGeocoder(app: Application) = Geocoder(app)

    @Provides
    fun provideFusedLocationProviderClient(app: Application) =
        LocationServices.getFusedLocationProviderClient(app)
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FrameworkRegionBindsModule {
    @Binds
    abstract fun bindLocationDataSource(locationDataSource: PlayServicesLocationDataSource): LocationDataSource

    @Binds
    abstract fun bindRegionDataSource(permissionChecker: GeocoderRegionDataSource): RegionDataSource
}