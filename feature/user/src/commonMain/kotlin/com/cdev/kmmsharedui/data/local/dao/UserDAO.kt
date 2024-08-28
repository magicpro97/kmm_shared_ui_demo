package com.cdev.kmmsharedui.data.local.dao

import com.cdev.kmmsharedui.data.local.entities.UserEntity
import com.cdev.kmmsharedui.data.local.entities.asDomainModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import com.cdev.kmmsharedui.model.UserDomainModel
 interface UserDAO {

    suspend fun getUsers(): List<UserDomainModel>
    suspend fun saveUsers(userEntities: List<UserEntity>)
    suspend fun saveUser(userEntity: UserEntity)
}

class UserDAOImpl(private val realm: Realm) : UserDAO {
    override suspend fun getUsers(): List<UserDomainModel> {
        return realm.query<UserEntity>().find().asDomainModel()
    }

    override suspend fun saveUsers(userEntities: List<UserEntity>) {
        userEntities.forEach {
            saveUser(it)
        }
    }

    override suspend fun saveUser(userEntity: UserEntity) {
        realm.query<UserEntity>("id = $0", userEntity.id).find().ifEmpty {
            realm.writeBlocking {
                this.copyToRealm(
                    userEntity
                )
            }
        }
    }
}