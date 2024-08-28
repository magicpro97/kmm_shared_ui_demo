package com.cdev.kmmsharedui.data.local.entities

import io.realm.kotlin.types.RealmObject
import com.cdev.kmmsharedui.model.UserDomainModel

open class UserEntity : RealmObject {
    var id: String = ""
    var name: String = ""
}

fun UserEntity.asDomainModel() = UserDomainModel(
    id,
    name = name,
)

fun List<UserEntity>.asDomainModel() = map {
    it.asDomainModel()
}
