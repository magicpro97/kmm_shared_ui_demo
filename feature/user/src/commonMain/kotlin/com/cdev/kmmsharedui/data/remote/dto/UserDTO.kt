package com.cdev.kmmsharedui.data.remote.dto

import com.cdev.kmmsharedui.model.UserDomainModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserDTO(
    @SerialName("id")
    val id: String,
    @SerialName("login")
    val name: String,
)

fun List<UserDTO>.asDomainModel(): List<UserDomainModel> {
    return this.map {
        UserDomainModel(
            id = it.id,
            name = it.name,
        )
    }
}