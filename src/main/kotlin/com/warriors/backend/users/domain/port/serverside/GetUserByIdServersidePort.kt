package com.warriors.backend.users.domain.port.serverside

import com.warriors.backend.users.domain.model.User
import java.util.UUID

interface GetUserByIdServersidePort {
    operator fun invoke(id : UUID) : User
}