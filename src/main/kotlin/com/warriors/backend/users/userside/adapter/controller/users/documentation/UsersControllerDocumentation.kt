package com.warriors.backend.users.userside.adapter.controller.users.documentation

import com.warriors.backend.users.domain.model.User
import com.warriors.backend.users.userside.restressources.UserRestRessource
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

interface UsersControllerDocumentation {


    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "the list of the users"
            )
        ]
    )
    @Operation(
        summary = "get the list of the users",
        description = "Get the list of all the users in the database",
        security = [SecurityRequirement(name = "")]
    )
    fun getAll() : ResponseEntity<List<UserRestRessource>>


    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "the required user "
            ),
            ApiResponse(
                responseCode = "404",
                description = "L'utilisateur n'existe pas",
                content = [Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)]
            )
        ]
    )
    @Operation(
        summary = "get the wanted user",
        description = "Get the wanted user by his Id",
        security = [SecurityRequirement(name = "")]
    )
    fun getById(@PathVariable id: UUID): ResponseEntity<UserRestRessource>

}