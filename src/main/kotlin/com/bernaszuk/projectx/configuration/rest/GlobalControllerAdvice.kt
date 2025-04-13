package com.bernaszuk.projectx.configuration.rest

import jakarta.validation.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.Instant

data class ErrorResponse(
    val timestamp: Instant = Instant.now(),
    val error: String,
    val status: Int,
    val message: String?,
)

@RestControllerAdvice
class GlobalControllerAdvice {
    @ExceptionHandler(ValidationException::class)
    fun handleUserNotFoundException(ex: ValidationException): ResponseEntity<ErrorResponse> {
        val validationErrorResponse =
            ErrorResponse(
                error = HttpStatus.BAD_REQUEST.reasonPhrase,
                status = HttpStatus.BAD_REQUEST.value(),
                message = ex.message,
            )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorResponse)
    }
}
