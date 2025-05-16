package com.warriors.backend.users.serverside.adapter.repository.users

import com.warriors.backend.users.domain.model.User
import com.warriors.backend.users.domain.port.serverside.GetAllUsersServersidePort
import com.warriors.backend.users.serverside.adapter.mysql.repository.UsersMySqlRepository
import com.warriors.backend.users.serverside.mapper.UserDocumentMapper.toUser
import org.springframework.stereotype.Repository

@Repository
class GetAllUserRepository(private val usersMySqlRepository: UsersMySqlRepository) : GetAllUsersServersidePort{
    override fun invoke(): List<User> = usersMySqlRepository.findAll().map { it.toUser() }

}