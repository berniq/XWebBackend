package com.bernaszuk.projectx.user

import java.time.LocalDate

data class UpdateUserRequest(
    val username: String? = null,
    val handle: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val dateOfBirth: LocalDate? = null,
    val location: String? = null,
    val bio: String? = null,
)
