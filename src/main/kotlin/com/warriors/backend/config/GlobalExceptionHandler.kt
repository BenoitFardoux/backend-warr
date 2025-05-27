package com.warriors.backend.config
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.security.SignatureException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AccountStatusException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentialsException(exception: BadCredentialsException) : ProblemDetail {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, exception.message!!)
    }
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(exception: HttpMessageNotReadableException) : ProblemDetail {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.message!!)
    }

    @ExceptionHandler(AccountStatusException::class)
    fun handleAccountStatusException(exception: AccountStatusException) : ProblemDetail {
        return ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, exception.message!!)
    }

    @ExceptionHandler(AccessDeniedException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun handleAccessDeniedException(exception: AccessDeniedException) : ProblemDetail {
        return ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, exception.message!!)

    }

    @ExceptionHandler(SignatureException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun handleSignatureException(exception: SignatureException) : ProblemDetail {
        return ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, exception.message!!)
    }

    @ExceptionHandler(ExpiredJwtException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun handleExpiredJwtException(exception: ExpiredJwtException) : ProblemDetail {
        return ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, exception.message!!)
    }

    @ExceptionHandler(AuthenticationException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleAuthenticationException(exception: AuthenticationException) : ProblemDetail {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, exception.message!!)
    }

    @ExceptionHandler(NotImplementedError::class)
    fun handleNotImplementedError(exception: NotImplementedError) : ProblemDetail {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_IMPLEMENTED, exception.message!!)
    }
}