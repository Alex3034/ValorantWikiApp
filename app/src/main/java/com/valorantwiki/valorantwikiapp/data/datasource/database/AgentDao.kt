package com.valorantwiki.valorantwikiapp.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.valorantwiki.valorantwikiapp.data.Agent

@Dao
interface AgentDao {
    @Query("SELECT * FROM Agent")
    suspend fun getAllAgents(): List<Agent>

    @Query("SELECT * FROM Agent WHERE uuid = :uuid")
    suspend fun getAgentById(uuid: String): Agent?

    @Query("SELECT COUNT(*) FROM Agent")
    suspend fun countAgents(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(agents: List<Agent>)
}