package com.warriors.backend.users.serverside.adapter.repository.users

import com.warriors.backend.users.domain.model.User
import com.warriors.backend.users.domain.port.serverside.LoginUserServersidePort
import com.warriors.backend.users.serverside.adapter.mysql.repository.UsersMySqlRepository
import com.warriors.backend.users.serverside.exception.UserDontExistException
import com.warriors.backend.users.serverside.mapper.UserDocumentMapper.toUser
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Repository


@Repository
class LoginUserRepository(
    private val usersMySqlRepository: UsersMySqlRepository,
    private val authenticationManager: AuthenticationManager
) : LoginUserServersidePort{

    override fun invoke(email: String, passord: String): User {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(email,passord))

        return usersMySqlRepository.getUserDocumentByEmail(email)?.toUser() ?: throw UserDontExistException("The user with email ${email} don't exist")
    }

}