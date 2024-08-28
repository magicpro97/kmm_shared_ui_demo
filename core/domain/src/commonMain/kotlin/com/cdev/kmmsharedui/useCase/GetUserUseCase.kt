package com.cdev.kmmsharedui.useCase

import kotlinx.coroutines.flow.Flow
import com.cdev.kmmsharedui.model.DomainResult
import com.cdev.kmmsharedui.model.UserDomainModel

interface GetUserUseCase {
    operator fun invoke(): Flow<DomainResult<List<UserDomainModel>>>
}