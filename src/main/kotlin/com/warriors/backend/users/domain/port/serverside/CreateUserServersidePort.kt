package com.warriors.backend.users.domain.port.serverside

import com.warriors.backend.users.domain.model.User


fun interface CreateUserServersidePort {
    operator fun invoke(user: User): User
}
