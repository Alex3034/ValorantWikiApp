package com.valorantwiki.valorantwikiapp

import androidx.test.rule.GrantPermissionRule
import com.valorantwiki.valorantwikiapp.framework.agent.database.AgentDao
import com.valorantwiki.valorantwikiapp.framework.agent.database.DbAgent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class ExampleInstrumentedTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val locationPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        "android.permission.ACCESS_COARSE_LOCATION"
    )

    @Inject
    lateinit var agentsDao: AgentDao

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun check_4_items_db() = runTest {
        agentsDao.save(buildDatabaseAgents("1", "2", "3", "4"))
        val agents = agentsDao.getAllAgents().first()
        assertEquals("4", agents.size.toString())
    }

    @Test
    fun check_6_items_db() = runTest {
        agentsDao.save(buildDatabaseAgents("1", "2", "3", "4", "5", "6"))
        assertEquals("6", agentsDao.getAllAgents().first().size.toString())
    }
}

fun buildDatabaseAgents(vararg uuid: String) = uuid.map {
    DbAgent(
        uuid = it,
        displayName= "Name $uuid",
        description = "Description $uuid",
        displayIcon = "Icon",
        fullPortrait = "Portrait",
        background = "Background",
        backgroundGradientColors = listOf("Color1", "Color2", "Color3", "Color4"),
        isFavorite = false
    )
}