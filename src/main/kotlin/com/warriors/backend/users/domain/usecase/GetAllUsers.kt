package com.warriors.backend.users.domain.usecase

import com.warriors.backend.users.domain.port.serverside.GetAllUsersServersidePort
import org.springframework.stereotype.Component


@Component
class GetAllUsers(
    private val getAllUsersServersidePort: GetAllUsersServersidePort
) {
    operator fun invoke() = getAllUsersServersidePort()
}