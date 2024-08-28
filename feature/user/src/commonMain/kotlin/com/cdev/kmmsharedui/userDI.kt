package com.cdev.kmmsharedui


import com.cdev.kmmsharedui.data.UserRepositoryImpl
import com.cdev.kmmsharedui.data.local.dao.UserDAO
import com.cdev.kmmsharedui.data.local.dao.UserDAOImpl
import com.cdev.kmmsharedui.data.remote.service.UserKtorService
import com.cdev.kmmsharedui.data.remote.service.UserKtorServiceImpl
import com.cdev.kmmsharedui.domain.GetUserUseCaseImpl
import com.cdev.kmmsharedui.repository.UserRepository
import com.cdev.kmmsharedui.useCase.GetUserUseCase
import org.koin.dsl.module
import com.cdev.kmmsharedui.presentation.userdetail.UserDetailScreenModel

private val data = module {
     single<UserDAO> { UserDAOImpl(get()) }
     single<UserKtorService> { UserKtorServiceImpl(get()) }
     single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }
}

private val useCase = module {
    single<GetUserUseCase> { GetUserUseCaseImpl(get()) }
}

val screenModelsModule = module {
     factory { UserDetailScreenModel() }
}

val featureUserModule = data + useCase + screenModelsModule