package com.valorantwiki.valorantwikiapp.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.valorantwiki.valorantwikiapp.domain.Agent

@Database(entities = [DbAgent::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AgentsDataBase: RoomDatabase() {
    abstract val agentDao: AgentDao
}