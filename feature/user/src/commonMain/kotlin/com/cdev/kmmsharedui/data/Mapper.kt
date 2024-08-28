package com.cdev.kmmsharedui.data

import com.cdev.kmmsharedui.data.local.entities.UserEntity
import com.cdev.kmmsharedui.data.remote.dto.UserDTO
import com.cdev.kmmsharedui.model.UserDomainModel

fun UserDomainModel.asDao(): UserEntity {
    return UserEntity().also {
        it.id = this.id
        it.name = this.name
    }
}

fun List<UserDomainModel>.asDao() = map {
        it.asDao()
}

fun UserDTO.asDomainModel(): UserDomainModel {
    return UserDomainModel(
        id = this.id,
        name = this.name
    )
}
