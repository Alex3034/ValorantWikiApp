package com.valorantwiki.valorantwikiapp.framework.core

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FrameworkCoreModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(app, AgentsDataBase::class.java, "agent-db").build()

    @Provides
    fun provideAgentDao(dataBase: AgentsDataBase) = dataBase.agentDao()
}