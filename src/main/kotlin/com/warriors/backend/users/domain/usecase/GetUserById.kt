package com.warriors.backend.users.domain.usecase

import com.warriors.backend.users.domain.model.User
import com.warriors.backend.users.domain.port.serverside.GetUserByIdServersidePort
import org.springframework.stereotype.Component
import java.util.UUID


@Component
class GetUserById(private val getUserByIdServersidePort: GetUserByIdServersidePort) {
    operator fun invoke(id : UUID) : User{
        return getUserByIdServersidePort(id)
    }
}