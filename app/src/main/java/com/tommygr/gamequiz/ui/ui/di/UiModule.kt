package com.tommygr.gamequiz.ui.ui.di

import com.tommygr.gamequiz.ui.ui.viewmodels.ScreenState
import com.tommygr.gamequiz.ui.ui.viewmodels.ScreenStateImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UiModule {

    @Binds
    abstract fun bindScreenState(screenStateImpl: ScreenStateImpl): ScreenState
}
