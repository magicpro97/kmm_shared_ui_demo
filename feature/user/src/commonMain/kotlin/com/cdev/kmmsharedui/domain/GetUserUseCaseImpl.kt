package com.cdev.kmmsharedui.domain

import com.cdev.kmmsharedui.repository.UserRepository
import com.cdev.kmmsharedui.useCase.GetUserUseCase

class GetUserUseCaseImpl(
    private val userRepository: UserRepository,
): GetUserUseCase {
    override fun invoke() = userRepository.getUsers()
}