package com.warriors.backend.users.domain.usecase

import com.warriors.backend.users.serverside.adapter.repository.users.CreateUserRepository
import com.warriors.backend.users.domain.exception.MailInvalidException
import org.springframework.stereotype.Component
import com.warriors.backend.users.domain.model.User
import com.warriors.backend.users.domain.port.serverside.CreateUserServersidePort
import com.warriors.backend.utils.Utils

@Component
class CreateUser(private val createUserServersidePort: CreateUserServersidePort){
    operator fun invoke(user : User): User {
        Utils.emailIsOk(user.email).takeIf { !it }?.let {throw MailInvalidException("Email adress is invalid") }

        return createUserServersidePort(user)
    }
}