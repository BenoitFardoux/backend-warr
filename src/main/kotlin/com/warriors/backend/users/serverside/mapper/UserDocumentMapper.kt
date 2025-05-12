package com.warriors.backend.users.serverside.mapper

import com.warriors.backend.users.domain.model.User
import com.warriors.backend.users.serverside.dto.UserDocument

object UserDocumentMapper {
    fun UserDocument.toUser(): User {
        return User(
            id = this.id,
            username = this.username,
            email = this.email,
            password = this.password,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt,
            validated = false,
        )
    }
    fun User.toEntity(): UserDocument {
        return UserDocument(
            id = this.id,
            username = this.username,
            email = this.email,
            password = this.password,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt,
            validated = false,
        )
    }
}