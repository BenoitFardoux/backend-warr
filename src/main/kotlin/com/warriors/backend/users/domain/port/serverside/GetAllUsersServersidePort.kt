package com.warriors.backend.users.domain.port.serverside

import com.warriors.backend.users.domain.model.User

interface GetAllUsersServersidePort {
    operator fun invoke() : List<User>
}