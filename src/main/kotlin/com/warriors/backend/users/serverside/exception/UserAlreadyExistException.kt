package com.warriors.backend.users.serverside.exception

import com.warriors.backend.users.domain.exception.UserException

class UserAlreadyExistException(override val description: String) : UserException (description)