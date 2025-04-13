package com.bernaszuk.projectx.post

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class PostControllerAdvice {
    @ExceptionHandler(PostNotFoundException::class)
    fun handlePostNotFoundException(ex: PostNotFoundException): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
}
