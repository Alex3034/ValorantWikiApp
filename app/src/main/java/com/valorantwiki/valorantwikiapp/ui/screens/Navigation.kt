package com.valorantwiki.valorantwikiapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.valorantwiki.valorantwikiapp.App
import com.valorantwiki.valorantwikiapp.data.AgentRepository
import com.valorantwiki.valorantwikiapp.framework.AgentsRoomDataSource
import com.valorantwiki.valorantwikiapp.framework.AgentsServerDataSource
import com.valorantwiki.valorantwikiapp.framework.remote.RetrofitClient
import com.valorantwiki.valorantwikiapp.ui.screens.agents.AgentListScreen
import com.valorantwiki.valorantwikiapp.ui.screens.agents.AgentViewModel
import com.valorantwiki.valorantwikiapp.ui.screens.detail.DetailAgentScreen
import com.valorantwiki.valorantwikiapp.ui.screens.detail.DetailAgentViewModel
import com.valorantwiki.valorantwikiapp.usecases.AgentsUseCase
import com.valorantwiki.valorantwikiapp.usecases.FindAgentByIdUseCase
import com.valorantwiki.valorantwikiapp.usecases.ToggleFavoriteUseCase
import kotlinx.serialization.Serializable

@Serializable
object Agents

@Serializable
data class Detail(val id: String)

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val app = LocalContext.current.applicationContext as App
    val agentRepository = AgentRepository(
        AgentsRoomDataSource(app.db.agentDao),
        AgentsServerDataSource(RetrofitClient.instance)
    )

    NavHost(navController, startDestination = Agents) {
        composable<Agents> {
            AgentListScreen(
                onAgentClick = { agent ->
                    navController.navigate(Detail(agent.uuid))
                },
                viewModel { AgentViewModel(AgentsUseCase(agentRepository)) }
            )
        }

        composable<Detail> { backStackEntry ->
            val detail: Detail = backStackEntry.toRoute()
            DetailAgentScreen(
                viewModel {
                    DetailAgentViewModel(
                        detail.id,
                        FindAgentByIdUseCase(agentRepository),
                        ToggleFavoriteUseCase(agentRepository)
                    )
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}