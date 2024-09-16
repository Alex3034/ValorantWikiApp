package com.valorantwiki.valorantwikiapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.valorantwiki.valorantwikiapp.ui.screens.agents.AgentListScreen
import com.valorantwiki.valorantwikiapp.ui.screens.detail.DetailAgentScreen
import com.valorantwiki.valorantwikiapp.ui.screens.detail.DetailAgentViewModel
import kotlinx.serialization.Serializable

@Serializable
object Agents

@Serializable
data class Detail(val id: String)

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
            val detail:Detail = backStackEntry.toRoute()
            DetailAgentScreen(
                viewModel { DetailAgentViewModel(detail.id) },
                onBack = { navController.popBackStack() }
            )
        }
    }
}