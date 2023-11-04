package com.tommygr.gamequiz.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.NavHostFragment
import com.tommygr.gamequiz.R
import com.tommygr.gamequiz.databinding.ActivityMainBinding
import com.tommygr.gamequiz.ui.ui.viewmodels.MainScreenViewModel
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
            NavHost(navController = navController, startDestination = "home" ) {
                composable("home") {
                    MainScreen(navController = navController, mainScreenViewModel = mainViewModel)
                }
            }
        }
    }
}