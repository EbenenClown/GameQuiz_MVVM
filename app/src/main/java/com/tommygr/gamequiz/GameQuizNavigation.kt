package com.tommygr.gamequiz

import androidx.navigation.NavController
import com.tommygr.gamequiz.GameQuizDestinationsArgs.GAME_SIZE_ARG
import com.tommygr.gamequiz.GameQuizScreens.GAME_OPTIONS_SCREEN
import com.tommygr.gamequiz.GameQuizScreens.GAME_SCREEN
import com.tommygr.gamequiz.GameQuizScreens.HOME_SCREEN
import com.tommygr.gamequiz.GameQuizScreens.SETTINGS_SCREEN
import com.tommygr.gamequiz.GameQuizScreens.STATISTICS_SCREEN

private object GameQuizScreens {
    const val HOME_SCREEN = "home"
    const val GAME_OPTIONS_SCREEN = "gameOptions"
    const val GAME_SCREEN = "gameScreen"
    const val STATISTICS_SCREEN = "statistics"
    const val SETTINGS_SCREEN = "settings"
}

object GameQuizDestinationsArgs {
    const val GAME_SIZE_ARG = "gameSize"

}

object GameQuizDestinations {
    const val HOME_ROUTE = HOME_SCREEN
    const val GAME_OPTIONS_ROUTE = GAME_OPTIONS_SCREEN
    const val GAME_SCREEN_ROUTE = "$GAME_SCREEN/{$GAME_SIZE_ARG}"
    const val STATISTICS_ROUTE = STATISTICS_SCREEN
    const val SETTINGS_ROUTE = SETTINGS_SCREEN
}

class GameQuizNavigationActions(private val navController: NavController) {
    fun navigateToHome() {
        navController.navigate(GameQuizDestinations.HOME_ROUTE)
    }

    fun navigateToGameOptions() {
        navController.navigate(GameQuizDestinations.GAME_OPTIONS_ROUTE)
    }

    fun navigateToGameScreen(gameSize: Int) {
        navController.navigate("$GAME_SCREEN/$gameSize")
    }

    fun navigateToStatistics() {
        navController.navigate(STATISTICS_SCREEN)
    }

    fun navigateToSettings() {
        navController.navigate(SETTINGS_SCREEN)
    }
}