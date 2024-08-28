package com.cdev.kmmsharedui.repository

import kotlinx.coroutines.flow.Flow
import com.cdev.kmmsharedui.model.DomainResult
import com.cdev.kmmsharedui.model.UserDomainModel

interface UserRepository {
    fun getUsers(): Flow<DomainResult<List<UserDomainModel>>>

    suspend fun getUser(
        name: String
    ): UserDomainModel
}