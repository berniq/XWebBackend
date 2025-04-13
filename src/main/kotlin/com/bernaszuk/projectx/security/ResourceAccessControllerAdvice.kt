package com.bernaszuk.projectx.security

import com.bernaszuk.projectx.security.exception.ForbiddenResourceActionException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ResourceAccessControllerAdvice {
    @ExceptionHandler(ForbiddenResourceActionException::class)
    fun handleUserDoesNotHaveAccessToTheResource(ex: ForbiddenResourceActionException): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.message)
}
