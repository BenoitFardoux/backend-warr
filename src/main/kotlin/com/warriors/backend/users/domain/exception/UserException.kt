package com.warriors.backend.users.domain.exception

abstract class UserException(
    open val description : String
) : RuntimeException(){
    override val message: String?
        get() = description
}