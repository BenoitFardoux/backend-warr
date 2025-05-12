package com.warriors.backend.users.userside.adapter.controller.users

import CreateUserRestRessource
import UsersControllerDocumentation
import com.warriors.backend.users.domain.model.User
import com.warriors.backend.users.domain.usecase.CreateUser
import com.warriors.backend.users.serverside.mapper.UserDocumentMapper.toEntity
import com.warriors.backend.users.userside.mapper.UsersMapper.toRestRessoure
import com.warriors.backend.users.userside.mapper.UsersMapper.toUser
import com.warriors.backend.users.userside.restressources.UserRestRessource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI


@RestController
@RequestMapping("/v1/user")
class UsersController(
    val createUserUsecase : CreateUser
) : UsersControllerDocumentation {


    @PostMapping
    override fun create(@RequestBody user : CreateUserRestRessource) : ResponseEntity<UserRestRessource> {
        val utilisateurCree : User = createUserUsecase(user.toUser())
        return ResponseEntity.created(URI("/${utilisateurCree.id}")).body(utilisateurCree.toRestRessoure())

    }

    @GetMapping
    override fun getAllUsers(): ResponseEntity<List<UserRestRessource>> {
        TODO("Not yet implemented")
    }

    @GetMapping("/{id}")
    override fun getById(@PathVariable id: String): ResponseEntity<UserRestRessource> {
        TODO("Not yet implemented")
    }
}