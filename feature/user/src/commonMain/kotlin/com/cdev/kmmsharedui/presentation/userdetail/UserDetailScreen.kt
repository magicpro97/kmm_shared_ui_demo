package com.cdev.kmmsharedui.presentation.userdetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun VoucherDetail(state: UserDetailState, navigator: Navigator) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "User Detail") },
                navigationIcon =  {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.clickable {
                            navigator.pop()
                        }
                    )
                },
            )
        }
    ) {
        when (state) {
            is UserDetailState.Success -> {
                Text(
                    text = state.user.name,
                    style = MaterialTheme.typography.h3
                )
            }

            else -> {}
        }
    }
}