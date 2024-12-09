package com.valorantwiki.valorantwikiapp.framework.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.valorantwiki.valorantwikiapp.framework.agent.database.AgentDao
import com.valorantwiki.valorantwikiapp.framework.agent.database.DbAgent

@Database(entities = [DbAgent::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AgentsDataBase: RoomDatabase() {
    abstract fun agentDao(): AgentDao
}