package com.valorantwiki.valorantwikiapp.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.valorantwiki.valorantwikiapp.data.Agent
import kotlinx.coroutines.flow.Flow

@Dao
interface AgentDao {
    @Query("SELECT * FROM Agent")
    fun getAllAgents(): Flow<List<Agent>>

    @Query("SELECT * FROM Agent WHERE uuid = :uuid")
    fun getAgentById(uuid: String): Flow<Agent?>

    @Query("SELECT COUNT(*) FROM Agent")
    suspend fun countAgents(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(agents: List<Agent>)
}