package com.warriors.backend.users.userside.adapter.controller.auth.documentation

import CreateUserRestRessource
import com.warriors.backend.users.userside.restressources.LoginResponse
import com.warriors.backend.users.userside.restressources.UserLoginRestRessource
import com.warriors.backend.users.userside.restressources.UserRestRessource
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.ResponseEntity

interface AuthenticationControllerDocumentation {
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "The api key who allow to login"
            )
        ]
    )
    @Operation(
        summary = "Log into the API",
        description = "Return the api key",
        security = [SecurityRequirement(name="")]
    )
    fun login (userLoginRestRessource: UserLoginRestRessource) : ResponseEntity<LoginResponse>
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "the user has been created"
            )
        ]
    )
    @Operation(
        summary = "Create a new user",
        description = "Create a new user in database",
        security = [SecurityRequirement(name = "")]
    )
    fun register(user : CreateUserRestRessource) : ResponseEntity<UserRestRessource>

}