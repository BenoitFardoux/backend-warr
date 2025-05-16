package com.warriors.backend.users.serverside.adapter.mysql.repository
import com.warriors.backend.users.serverside.dto.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UsersMySqlRepository : JpaRepository<UserEntity, UUID>{
    fun getUserDocumentByEmail(mail: String) : UserEntity?
    fun existsByEmail(mail : String) : Boolean
    fun getUsernameByEmail(mail: String) : String
}