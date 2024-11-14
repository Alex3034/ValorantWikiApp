package com.valorantwiki.valorantwikiapp.framework.agent

import com.valorantwiki.valorantwikiapp.domain.agent.data.AgentLocalDataSource
import com.valorantwiki.valorantwikiapp.domain.agent.data.AgentsRemoteDataSource
import com.valorantwiki.valorantwikiapp.framework.agent.database.AgentsRoomDataSource
import com.valorantwiki.valorantwikiapp.framework.agent.network.AgentsServerDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FrameworkMovieModule {
    @Binds
    abstract fun bindLocalDataSource(localDataSource: AgentsRoomDataSource): AgentLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: AgentsServerDataSource): AgentsRemoteDataSource
}