package com.warriors.backend.users.userside.adapter.controller.auth

import CreateUserRestRessource
import com.warriors.backend.users.domain.model.User
import com.warriors.backend.users.domain.usecase.CreateUser
import com.warriors.backend.users.domain.usecase.LoginUser
import com.warriors.backend.users.serverside.mapper.UserDocumentMapper.toEntity
import com.warriors.backend.users.userside.adapter.controller.auth.documentation.AuthenticationControllerDocumentation
import com.warriors.backend.users.userside.mapper.UsersMapper.toRestRessoure
import com.warriors.backend.users.userside.mapper.UsersMapper.toUser
import com.warriors.backend.users.userside.restressources.LoginResponse
import com.warriors.backend.users.userside.restressources.UserLoginRestRessource
import com.warriors.backend.users.userside.restressources.UserRestRessource
import com.warriors.backend.utils.JwtService
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RequestMapping("/auth")
@RestController
class AuthenticationController(
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder,
    private val loginUser: LoginUser,
    private val createUserUsecase: CreateUser
) :
    AuthenticationControllerDocumentation {

    @PostMapping("/login")
    override fun login(userLoginRestRessource: UserLoginRestRessource): ResponseEntity<LoginResponse> {
        val user = loginUser(userLoginRestRessource.email,userLoginRestRessource.password).toEntity()
        val jwtToken = jwtService.generateToken(user)
        val loginResponse = LoginResponse(token = jwtToken, expiresIn = jwtService.getExpirationTime())

        return ResponseEntity.ok(loginResponse)
    }

    @PostMapping("/register")
    override fun register(@RequestBody user : CreateUserRestRessource) : ResponseEntity<UserRestRessource> {
        val passwordEncoder = passwordEncoder.encode(user.password)
        val userToCreate = user.copy(password = passwordEncoder)
        val utilisateurCree : User = createUserUsecase(userToCreate.toUser())
        return ResponseEntity.created(URI("/${utilisateurCree.id}")).body(utilisateurCree.toRestRessoure())

    }

}