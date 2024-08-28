package com.cdev.kmmsharedui.data

import CoroutineProvider
import com.cdev.kmmsharedui.data.local.dao.UserDAO
import com.cdev.kmmsharedui.data.remote.dto.asDomainModel
import com.cdev.kmmsharedui.data.remote.service.UserKtorService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import com.cdev.kmmsharedui.model.DomainResult
import com.cdev.kmmsharedui.model.UserDomainModel
import networkBoundResource
import com.cdev.kmmsharedui.repository.UserRepository

class UserRepositoryImpl(
    private val ktorService: UserKtorService,
    private val realmService: UserDAO,
    private val coroutineProvider: CoroutineProvider,
) : UserRepository {

    override fun getUsers(): Flow<DomainResult<List<UserDomainModel>>> = networkBoundResource(
        query = {
            flowOf(realmService.getUsers())
        },
        fetch = {
            ktorService.getUsers()
        },
        saveFetchResult = { users ->
            realmService.saveUsers(users.asDomainModel().asDao())
        },
        isReceiveDataQuery = true,
    ).flowOn(coroutineProvider.io)

    override suspend fun getUser(
        name: String
    ): UserDomainModel = withContext(coroutineProvider.io) {
        ktorService.getUser(name).asDomainModel()
    }
}