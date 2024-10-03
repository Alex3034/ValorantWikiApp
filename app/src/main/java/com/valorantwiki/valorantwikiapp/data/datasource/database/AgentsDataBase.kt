package com.valorantwiki.valorantwikiapp.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.valorantwiki.valorantwikiapp.data.Agent

@Database(entities = [Agent::class], version = 1, exportSchema = false)
abstract class AgentsDataBase: RoomDatabase() {
    abstract val agentDao: AgentDao
}