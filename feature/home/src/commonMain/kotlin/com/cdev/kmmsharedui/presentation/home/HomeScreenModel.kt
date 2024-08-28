package com.cdev.kmmsharedui.presentation.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.cdev.kmmsharedui.model.DomainResult
import com.cdev.kmmsharedui.model.asResult
import com.cdev.kmmsharedui.useCase.GetUserUseCase

class HomeScreenModel(
    private val getUsersUseCase: GetUserUseCase,
) : ScreenModel {

    private val _state = MutableStateFlow<HomeScreenState>(HomeScreenState.Idle)
    var state = _state.asStateFlow()

    init {
        onIntent(HomeScreenSideEvent.GetUsers)
    }

    fun onIntent(intent: HomeScreenSideEvent) =
        when (intent) {
            is HomeScreenSideEvent.GetUsers -> {
                getUsers()
            }
        }

    private fun getUsers() {
        screenModelScope.launch {
            doGetUsersUseCase()
        }
    }

    private suspend fun doGetUsersUseCase() {
        getUsersUseCase().asResult().collectLatest { result ->
            when (result) {
                is DomainResult.Error -> {
                    _state.update {
                        HomeScreenState.Error(result.exception.message)
                    }
                }

                is DomainResult.Idle -> {
                    _state.update {
                        HomeScreenState.Idle
                    }
                }

                is DomainResult.Loading -> {
                    _state.update {
                        HomeScreenState.Loading
                    }
                }

                is DomainResult.Success -> {
                    result.data.run {
                        when (this) {
                            is DomainResult.Success -> {
                                _state.update {
                                    HomeScreenState.Success(this.data)
                                }
                            }

                            DomainResult.Loading -> {
                                _state.update {
                                    HomeScreenState.Loading
                                }
                            }

                            else -> {}
                        }
                    }
                }
            }
        }
    }
}
