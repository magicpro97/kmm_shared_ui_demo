package com.cdev.kmmsharedui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cdev.kmmsharedui.model.UserDomainModel
import com.cdev.kmmsharedui.presentation.home.HomeScreenModel
import com.cdev.kmmsharedui.presentation.home.HomeScreenUI
import com.cdev.kmmsharedui.presentation.userdetail.VoucherDetail
import com.cdev.kmmsharedui.presentation.userdetail.UserDetailScreenModel
import com.cdev.kmmsharedui.presentation.userdetail.UserDetailSideEvent

data object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel: HomeScreenModel = getScreenModel()
        val state by screenModel.state.collectAsState()
        HomeScreenUI(state) {
            navigator.push(UserDetailScreen(it))
        }
    }
}

data class UserDetailScreen(val userDomainModel: UserDomainModel): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel: UserDetailScreenModel = getScreenModel()

        LaunchedEffect(key1 = Unit) {
            screenModel.onIntent(UserDetailSideEvent.CacheUser(userDomainModel))
        }

        val state by screenModel.state.collectAsState()
        VoucherDetail(state, navigator)
    }
}
