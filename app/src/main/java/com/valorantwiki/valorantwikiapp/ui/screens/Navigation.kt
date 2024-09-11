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
import com.valorantwiki.valorantwikiapp.ui.screens.detail.DetailAgentViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "agentList") {
        composable("agentList") {
            AgentListScreen(
                onAgentClick = { agent ->
                    navController.navigate("agentDetail/${agent.uuid}")
                }
            )
        }

        composable(
            "agentDetail/{agentUuid}",
            arguments = listOf(navArgument("agentUuid") { type = NavType.IntType })
        ) { backStackEntry ->
            val agentUuid = requireNotNull(backStackEntry.arguments?.getInt("agentUuid"))
            DetailAgentScreen(
                viewModel { DetailAgentViewModel(agentUuid) },
                onBack = { navController.popBackStack() }
            )

        }
    }
}