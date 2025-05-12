package com.warriors.backend.users.userside.adapter.controller.users

import com.warriors.backend.users.domain.exception.MailInvalidException
import com.warriors.backend.users.serverside.exception.UserAlreadyExistException
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice(assignableTypes = [UsersController::class])
class UserControllerAdvice {
    @ExceptionHandler(NotImplementedError::class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    fun error501(exception : NotImplementedError) : ProblemDetail {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_IMPLEMENTED, exception.message)
    }

    @ExceptionHandler(MailInvalidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun error400(exception : MailInvalidException) : ProblemDetail{
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.message)
    }

    @ExceptionHandler(UserAlreadyExistException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun error409(exception: UserAlreadyExistException): ProblemDetail{
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,exception.message)
    }

}