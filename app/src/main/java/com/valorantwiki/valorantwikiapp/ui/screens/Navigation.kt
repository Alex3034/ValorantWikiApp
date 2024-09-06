package com.valorantwiki.valorantwikiapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.valorantwiki.valorantwikiapp.ui.screens.agents.AgentListScreen
import com.valorantwiki.valorantwikiapp.ui.screens.agents.AgentViewModel
import com.valorantwiki.valorantwikiapp.ui.screens.detail.DetailAgentScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: AgentViewModel = viewModel()

    NavHost(navController, startDestination = "agentList") {
        composable("agentList") {
            AgentListScreen(
                onAgentClick = { agent ->
                    navController.navigate("agentDetail/${agent.uuid}")
                }
            )
        }

        composable(
            "agentDetail/{agentId}",
            arguments = listOf(navArgument("agentId") { type = NavType.StringType })
        ) { backStackEntry ->
            val agentId = requireNotNull(backStackEntry.arguments?.getString("agentId"))
            val agent = viewModel.getAgentById(agentId)
            if (agent != null) {
                DetailAgentScreen(
                    agent = agent,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}