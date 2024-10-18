package com.valorantwiki.valorantwikiapp.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.valorantwiki.valorantwikiapp.domain.Agent
import kotlinx.coroutines.flow.Flow

@Dao
interface AgentDao {
    @Query("SELECT * FROM DbAgent")
    fun getAllAgents(): Flow<List<DbAgent>>

    @Query("SELECT * FROM DbAgent WHERE uuid = :uuid")
    fun getAgentById(uuid: String): Flow<DbAgent?>

    @Query("SELECT COUNT(*) FROM DbAgent")
    suspend fun countAgents(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(agents: List<DbAgent>)
}