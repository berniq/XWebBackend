package com.bernaszuk.projectx.user

import com.bernaszuk.projectx.AbstractIntegrationTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class UserControllerIT : AbstractIntegrationTest() {
    @AfterEach
    fun cleanup() {
        userRepository.deleteAll()
    }

    @Test
    fun `Create new user`() {
        val newUser = CreateUserRequest(
            username = "John",
            handle = "12345",
            firstName = "John",
            lastName = "Doe",
            email = "john.doe@example.com",
            dateOfBirth = LocalDate.of(1980, 1, 1),
            location = "Polska"
        )
        val createdUser

    }
}
