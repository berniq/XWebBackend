package com.bernaszuk.projectx.user

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class UserControllerAdvice {
    @ExceptionHandler(UserNotFoundExceptionById::class)
    fun handleUserNotFoundExceptionById(ex: UserNotFoundExceptionById): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)

    @ExceptionHandler(UserNotFoundExceptionByUserName::class)
    fun handleUserNotFoundExceptionByUserName(ex: UserNotFoundExceptionByUserName): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
}
