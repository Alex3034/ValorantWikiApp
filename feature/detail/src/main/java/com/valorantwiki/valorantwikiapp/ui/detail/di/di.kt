package com.valorantwiki.valorantwikiapp.ui.detail.di

import androidx.lifecycle.SavedStateHandle
import com.valorantwiki.valorantwikiapp.ui.common.Detail
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DetailViewModelModule {
    @Provides
    @ViewModelScoped
    @AgentUuid
    fun provideAgentId(savedStateHandle: SavedStateHandle): String {
        return (savedStateHandle[Detail.toString()] ?: -1).toString()
    }
}