package com.warriors.backend.users.domain.model

import java.time.LocalDate
import java.util.UUID

data class User (
    val id : UUID? = null,
    val username : String,
    val email : String,
    val password : String,
    val createdAt : LocalDate,
    val updatedAt : LocalDate,
    val validated : Boolean
)