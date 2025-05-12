package com.warriors.backend.users.userside.restressources

import java.time.LocalDate
import java.util.UUID

data class UserRestRessource (
    val id : UUID?,
    val username : String,
    val email : String,
    val createdAt : LocalDate,
    val updatedAt : LocalDate,
    val validated : Boolean
)