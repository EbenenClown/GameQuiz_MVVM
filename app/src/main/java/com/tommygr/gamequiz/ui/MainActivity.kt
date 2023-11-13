package com.tommygr.gamequiz.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.tommygr.gamequiz.ui.screens.GameOptionsScreen
import com.tommygr.gamequiz.ui.screens.MainScreen
import com.tommygr.gamequiz.ui.viewmodels.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainViewModel by viewModels<MainScreenViewModel>()
        mainViewModel.syncQuizElements()
        mainViewModel.getUserNameAndCheckForLoggedInStatus()

        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController, startDestination = "home",
                modifier = Modifier
                    .fillMaxSize(),
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None },
            ) {
                composable("home") {
                    MainScreen(navController = navController, mainScreenViewModel = mainViewModel)

                }

                navigation(startDestination = "startGame", route = "game") {
                    composable("startGame") {
                        GameOptionsScreen(navController = navController)
                    }
                }
            }
        }
    }
}