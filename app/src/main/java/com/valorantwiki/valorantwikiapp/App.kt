package com.valorantwiki.valorantwikiapp

import android.app.Application
import androidx.room.Room
import com.valorantwiki.valorantwikiapp.data.datasource.database.AgentsDataBase

class App: Application() {

    lateinit var db: AgentsDataBase
    private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            AgentsDataBase::class.java,
            "agents.db"
        ).build()
    }
}