package com.valorantwiki.valorantwikiapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.valorantwiki.valorantwikiapp.ui.agent.AgentListScreen
import com.valorantwiki.valorantwikiapp.ui.detail.DetailAgentScreen
import com.valorantwiki.valorantwikiapp.ui.common.Agents
import com.valorantwiki.valorantwikiapp.ui.common.Detail

@Composable
fun Navigation() {
    val navController = rememberNavController()


    NavHost(navController, startDestination = Agents) {
        composable<Agents> {
            AgentListScreen(
                onAgentClick = { agent ->
                    navController.navigate(Detail(agent.uuid))
                }
            )
        }

        composable<Detail> { backStackEntry ->
            val detail: Detail = backStackEntry.toRoute()
            DetailAgentScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}