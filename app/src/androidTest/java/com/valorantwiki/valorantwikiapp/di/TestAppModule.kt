package com.valorantwiki.valorantwikiapp.di

import android.app.Application
import androidx.room.Room
import com.valorantwiki.valorantwikiapp.framework.core.AgentsDataBase
import com.valorantwiki.valorantwikiapp.framework.core.FrameworkCoreExtrasModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [FrameworkCoreExtrasModule::class]
)

object TestAppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AgentsDataBase {
        val db = Room.inMemoryDatabaseBuilder(
            app,
            AgentsDataBase::class.java
        )
            .setTransactionExecutor(Dispatchers.Main.asExecutor())
            .allowMainThreadQueries()
            .build()
        return db
    }
}