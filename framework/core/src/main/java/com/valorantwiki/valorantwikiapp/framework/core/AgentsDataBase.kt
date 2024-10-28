package com.valorantwiki.valorantwikiapp.framework.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [com.valorantwiki.valorantwikiapp.framework.agent.database.DbAgent::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AgentsDataBase: RoomDatabase() {
    abstract val agentDao: com.valorantwiki.valorantwikiapp.framework.agent.database.AgentDao
}