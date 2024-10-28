package com.valorantwiki.valorantwikiapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.valorantwiki.valorantwikiapp.App
import com.valorantwiki.valorantwikiapp.domain.agent.data.AgentRepository
import com.valorantwiki.valorantwikiapp.framework.agent.database.AgentsRoomDataSource
import com.valorantwiki.valorantwikiapp.framework.agent.network.AgentsServerDataSource
import com.valorantwiki.valorantwikiapp.framework.core.RetrofitClient
import com.valorantwiki.valorantwikiapp.ui.agent.AgentListScreen
import com.valorantwiki.valorantwikiapp.ui.agent.AgentViewModel
import com.valorantwiki.valorantwikiapp.ui.detail.DetailAgentScreen
import com.valorantwiki.valorantwikiapp.ui.detail.DetailAgentViewModel
import com.valorantwiki.valorantwikiapp.domain.agent.usecases.AgentsUseCase
import com.valorantwiki.valorantwikiapp.domain.agent.usecases.FindAgentByIdUseCase
import com.valorantwiki.valorantwikiapp.domain.agent.usecases.ToggleFavoriteUseCase
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
        AgentsServerDataSource(
            RetrofitClient.instance
        )
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