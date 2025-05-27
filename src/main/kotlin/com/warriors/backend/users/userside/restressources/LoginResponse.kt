package com.warriors.backend.users.userside.restressources

data class LoginResponse(
    val token: String,

    val expiresIn: Long
)
