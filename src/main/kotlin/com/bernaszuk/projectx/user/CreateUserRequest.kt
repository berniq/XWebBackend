package com.bernaszuk.projectx.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Past
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class CreateUserRequest(
    @NotBlank(message = "Username cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric")
    @Size(max = 64, message = "Username too long, no more than 64 characters.")
    val username: String,
    @NotBlank(message = "handle cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Handle must be alphanumeric")
    @Size(max = 64, message = "Handle too long, no more than 64 characters.")
    val handle: String,
    @Email(message = "First name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "First name must be alphanumeric")
    @Size(max = 64, message = "First name too long, no more than 64 characters.")
    val firstName: String,
    @NotBlank(message = "Last name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Last name must be alphanumeric")
    @Size(max = 64, message = "Last name too long, no more than 64 characters.")
    val lastName: String,
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Must be a valid email address")
    @Size(max = 256, message = "Email too long.")
    val email: String,
    @NotBlank(message = "Date of Birth cannot be empty")
    @Past
    val dateOfBirth: LocalDate,
    @NotBlank(message = "Location cannot be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Location must contain only letters")
    @Size(max = 256, message = "Location too long.")
    val location: String,
)
