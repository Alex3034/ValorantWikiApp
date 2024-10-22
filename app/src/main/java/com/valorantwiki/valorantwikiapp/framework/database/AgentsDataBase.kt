package com.valorantwiki.valorantwikiapp.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DbAgent::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AgentsDataBase: RoomDatabase() {
    abstract val agentDao: AgentDao
}