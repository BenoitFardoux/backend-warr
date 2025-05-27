package com.warriors.backend.users.domain.port.serverside

import com.warriors.backend.users.domain.model.User

interface LoginUserServersidePort {
    operator fun invoke(email : String, passord : String) : User
}