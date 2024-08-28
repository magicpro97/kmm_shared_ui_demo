package com.cdev.kmmsharedui

import org.koin.dsl.module
import com.cdev.kmmsharedui.presentation.home.HomeScreenModel

private val screenModel = module {
    single { HomeScreenModel(get()) }
}

val featureHomeModule = screenModel