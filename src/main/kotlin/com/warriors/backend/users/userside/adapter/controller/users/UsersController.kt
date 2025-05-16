package com.warriors.backend.users.userside.adapter.controller.users

import CreateUserRestRessource
import UsersControllerDocumentation
import com.warriors.backend.users.domain.model.User
import com.warriors.backend.users.domain.usecase.CreateUser
import com.warriors.backend.users.domain.usecase.GetAllUsers
import com.warriors.backend.users.domain.usecase.GetUserById
import com.warriors.backend.users.serverside.mapper.UserDocumentMapper.toEntity
import com.warriors.backend.users.userside.mapper.UsersMapper.toRestRessoure
import com.warriors.backend.users.userside.mapper.UsersMapper.toUser
import com.warriors.backend.users.userside.restressources.UserRestRessource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.UUID


@RestController
@RequestMapping("/v1/user")
class UsersController(
    val createUserUsecase : CreateUser,
    val getUserById: GetUserById,
    val getAllUsers: GetAllUsers
) : UsersControllerDocumentation {


    @PostMapping
    override fun create(@RequestBody user : CreateUserRestRessource) : ResponseEntity<UserRestRessource> {
        val utilisateurCree : User = createUserUsecase(user.toUser())
        return ResponseEntity.created(URI("/${utilisateurCree.id}")).body(utilisateurCree.toRestRessoure())

    }

    @GetMapping
    override fun getAll(): ResponseEntity<List<UserRestRessource>> {
        val users = getAllUsers()
        return ResponseEntity.ok(users.map { it.toRestRessoure() })
    }

    @GetMapping("/{id}")
    override fun getById(@PathVariable id: UUID): ResponseEntity<UserRestRessource> {
        return ResponseEntity.ok(getUserById(id).toRestRessoure())
    }
}