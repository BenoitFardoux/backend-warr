package com.warriors.backend.users.serverside.adapter.repository.users
import com.warriors.backend.users.domain.model.User
import com.warriors.backend.users.domain.port.serverside.CreateUserServersidePort
import com.warriors.backend.users.serverside.adapter.mysql.repository.UsersMySqlRepository
import com.warriors.backend.users.serverside.dto.UserEntity
import com.warriors.backend.users.serverside.exception.UserAlreadyExistException
import com.warriors.backend.users.serverside.mapper.UserDocumentMapper.toEntity
import com.warriors.backend.users.serverside.mapper.UserDocumentMapper.toUser
import org.springframework.stereotype.Repository

@Repository
class CreateUserRepository(val usersMySqlRepository: UsersMySqlRepository) : CreateUserServersidePort {
    override fun invoke(user: User): User {
        usersMySqlRepository.getUserDocumentByEmail(user.email)?.let { throw UserAlreadyExistException("the user with mail ${user.email} already exists.") }
        val createdUser = usersMySqlRepository.saveAndFlush<UserEntity>(user.toEntity())
        return createdUser.toUser()
    }
}