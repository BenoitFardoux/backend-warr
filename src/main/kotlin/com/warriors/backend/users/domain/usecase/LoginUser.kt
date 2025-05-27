package com.warriors.backend.users.domain.usecase

import com.warriors.backend.users.domain.port.serverside.LoginUserServersidePort
import org.springframework.stereotype.Component


@Component
class LoginUser(
    private val loginUserServersidePort: LoginUserServersidePort
) {
    operator fun invoke(email : String, password : String) = loginUserServersidePort(email, password)
}