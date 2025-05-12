package com.warriors.backend.users.serverside.adapter.mysql.repository
import com.warriors.backend.users.serverside.dto.UserDocument
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UsersMySqlRepository : JpaRepository<UserDocument, UUID>{
    fun getUserDocumentByEmail(mail: String) : UserDocument?
}