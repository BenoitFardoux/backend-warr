package com.warriors.backend.users.serverside.adapter.repository.users

import com.warriors.backend.users.domain.model.User
import com.warriors.backend.users.domain.port.serverside.GetUserByIdServersidePort
import com.warriors.backend.users.serverside.adapter.mysql.repository.UsersMySqlRepository
import com.warriors.backend.users.serverside.exception.UserDontExistException
import com.warriors.backend.users.serverside.mapper.UserDocumentMapper.toUser
import org.springframework.stereotype.Repository
import java.util.UUID


@Repository
class GetUserByIdRepository(private val mySqlRepository: UsersMySqlRepository) : GetUserByIdServersidePort{
    override fun invoke(id: UUID): User {
        if (mySqlRepository.existsById(id).not()) {
            throw UserDontExistException("the user with id $id don't exist")
        }

        return mySqlRepository.getReferenceById(id).toUser()
    }

}