package com.warriors.backend.users.fixture

import CreateUserRestRessource
import com.warriors.backend.users.serverside.dto.UserEntity
import java.time.LocalDate

object UserFixture {
    val createUserRestRessource = CreateUserRestRessource(
        username = "Azzazul",
        email = "azzazul@mail.com",
        password = "12345678"
    )
    val createUserRestRessourceInvalid = CreateUserRestRessource(
        username = "Azzazul",
        email = "aaaa",
        password = "123456789"
    )

    val userEntity = UserEntity(
        username = "Azzazul",
        email = "azzazul@mail.com",
        password = "12345678",
        id = null,
        createdAt = LocalDate.now(),
        updatedAt = LocalDate.now(),
        validated = false
    )
}