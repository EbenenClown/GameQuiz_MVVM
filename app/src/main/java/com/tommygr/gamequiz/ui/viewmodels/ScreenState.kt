package com.tommygr.gamequiz.ui.viewmodels

interface ScreenState {
    var currentScreen: Screen
}

class ScreenStateImpl : ScreenState {

    override var currentScreen = Screen.Home

}