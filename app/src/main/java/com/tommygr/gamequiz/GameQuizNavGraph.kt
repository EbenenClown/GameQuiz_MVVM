package com.tommygr.gamequiz

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tommygr.gamequiz.ui.screens.GameOptionsScreen
import com.tommygr.gamequiz.ui.screens.GameScreen
import com.tommygr.gamequiz.ui.screens.HomeScreen


@Composable
fun GameQuizNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = GameQuizDestinations.HOME_ROUTE,
    navActions: GameQuizNavigationActions = remember(navController) {
        GameQuizNavigationActions(navController)
    }
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        composable(GameQuizDestinations.HOME_ROUTE) {
            HomeScreen(
                onStartGameClick = { navActions.navigateToGameOptions() },
                onStatisticClick = { navActions.navigateToStatistics() },
                onSettingsClick = { navActions.navigateToSettings() }
                )
        }
        composable(GameQuizDestinations.GAME_OPTIONS_ROUTE) {
            GameOptionsScreen(
                onGameModeClicked = { entry ->
                    navActions.navigateToGameScreen(entry)
                }
            )
        }
        composable(GameQuizDestinations.GAME_SCREEN_ROUTE) {
            GameScreen()
        }
    }

}