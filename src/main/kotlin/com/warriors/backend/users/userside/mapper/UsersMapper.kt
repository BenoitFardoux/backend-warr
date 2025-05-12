package com.warriors.backend.users.userside.mapper

import CreateUserRestRessource
import com.warriors.backend.users.domain.model.User
import com.warriors.backend.users.userside.restressources.UserRestRessource
import java.time.LocalDate

object UsersMapper {
    fun CreateUserRestRessource.toUser(): User {
        return User(
            id = null,
            username = username,
            email = email,
            password = password,
            createdAt = LocalDate.now(),
            updatedAt = LocalDate.now(),
            validated = false
        )
    }

    fun User.toRestRessoure() : UserRestRessource {
        return UserRestRessource(
            username = this.username,
            email = this.email,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt,
            validated = this.validated,
            id = this.id
        )
    }
}