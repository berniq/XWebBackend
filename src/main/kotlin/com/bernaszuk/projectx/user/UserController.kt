package com.bernaszuk.projectx.user

import jakarta.validation.Valid
import jakarta.validation.ValidationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.util.UUID

@RestController
class UserController(
    val userService: UserService,
) {
    @PostMapping("/users")
    fun createUser(
        @Valid
        @RequestBody request: CreateUserRequest,
    ): User {
        val user = userService.createUser(request)
        return user
    }

    @GetMapping("/users")
    fun findUsers(
        @RequestParam page: Int = 0,
        @RequestParam size: Int = 5,
    ): UserResponse {
        val user = userService.findUsers(page, size)
        return user
    }

    @GetMapping("/users/@{userName}")
    fun findUserByUserName(
        @PathVariable("userName") userName: String,
    ): User = userService.findUserByUserName(userName)

    @GetMapping("/users/{userId}")
    fun getUserById(
        @PathVariable("userId") userId: UUID,
    ): User = userService.getUserById(userId)

    @PutMapping("/users/{userId}")
    fun updateUserProfile(
        @PathVariable("userId") userId: UUID,
        @RequestBody request: UpdateUserRequest,
    ): User {
        validateUpdateRequest(request)
        val user =
            getUserById(userId).apply {
                request.username?.let { username = it }
                request.handle?.let { handle = it }
                request.firstName?.let { firstName = it }
                request.lastName?.let { lastName = it }
                request.email?.let { email = it }
                request.dateOfBirth?.let { dateOfBirth = it }
                request.location?.let { location = it }
                request.bio?.let { bio = it }
            }
        return userService.updateUser(user)
    }

    @DeleteMapping("/users/{userId}")
    fun deleteUser(
        @PathVariable("userId") userId: UUID,
    ): ResponseEntity<Unit> {
        userService.deleteUser(userId)
        return ResponseEntity.noContent().build()
    }

    fun validateUpdateRequest(request: UpdateUserRequest) {
        if (request.username != null &&
            (
                request.username.isBlank() ||
                    request.username.length < 3 ||
                    request.username.length > 20 ||
                    !request.username.matches(Regex("^[a-zA-Z0-9]+$"))
            )

        ) {
            throw ValidationException("Wrong username")
        }

        if (request.handle != null &&
            (
                request.handle.isBlank() ||
                    request.handle.length < 3 ||
                    request.handle.length > 20 ||
                    !request.handle.matches(Regex("^[a-zA-Z0-9]+$"))
            )

        ) {
            throw ValidationException("Wrong handle")
        }

        if (request.firstName != null &&
            (
                request.firstName.isBlank() ||
                    request.firstName.length < 3 ||
                    request.firstName.length > 20 ||
                    request.firstName.first().isDigit() ||
                    !request.firstName.matches(Regex("^[a-zA-Z]+$"))
            )
        ) {
            throw ValidationException("Wrong first name")
        }

        if (request.lastName != null &&
            (
                request.lastName.isBlank() ||
                    request.lastName.length < 3 ||
                    request.lastName.length > 20 ||
                    request.lastName.first().isDigit() ||
                    !request.lastName.matches(Regex("^[a-zA-Z]+$"))
            )
        ) {
            throw ValidationException("Wrong last name")
        }
        if (request.email != null && !request.email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$"))) {
            throw ValidationException("Wrong email")
        }
        if (request.dateOfBirth != null &&
            request.dateOfBirth.isBefore(LocalDate.now())

        ) {
            throw ValidationException("Wrong date of birth")
        }
        if (request.location != null &&
            (
                request.location.isBlank() &&
                    request.location.length > 30 ||
                    !request.location.matches(Regex("^[a-zA-Z]+( [a-zA-Z]+)*$"))
            )
        ) {
            throw ValidationException("Wrong location")
        }
        if (request.bio != null &&
            request.bio.length < 1000
        ) {
            throw ValidationException("Wrong bio")
        }
    }
}
