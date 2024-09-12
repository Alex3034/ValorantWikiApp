package com.valorantwiki.valorantwikiapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.valorantwiki.valorantwikiapp.ui.screens.agents.AgentListScreen
import com.valorantwiki.valorantwikiapp.ui.screens.detail.DetailAgentScreen
import com.valorantwiki.valorantwikiapp.ui.screens.detail.DetailAgentViewModel

sealed class NavScreen(val route: String) {
    data object Agent : NavScreen("agents")
    data object Detail : NavScreen("detail/{${NavArg.AgentId.key}}") {
        fun createRoute(agentId: String) = "detail/$agentId"
    }
}

enum class NavArg(val key: String) {
    AgentId("agentUuid")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = NavScreen.Agent.route) {
        composable(NavScreen.Agent.route) {
            AgentListScreen(
                onAgentClick = { agent ->
                    navController.navigate(NavScreen.Detail.createRoute(agent.uuid))
                }
            )
        }

        composable(
            NavScreen.Detail.route,
            arguments = listOf(navArgument(NavArg.AgentId.key) { type = NavType.StringType })
        ) { backStackEntry ->
            val agentUuid = requireNotNull(backStackEntry.arguments?.getString(NavArg.AgentId.key))
            DetailAgentScreen(
                viewModel { DetailAgentViewModel(agentUuid) },
                onBack = { navController.popBackStack() }
            )
        }
    }
}