package com.bernaszuk.projectx.user

import java.time.LocalDate

class UserTestDataFactory {
    companion object {
        fun createTestUser(testId: Int = 1) =
            User(
                handle = "handle$testId",
                username = "username$testId",
                bio = "bio$testId",
                firstName = "firstName$testId",
                lastName = "lastName$testId",
                email = "email$testId@test.com",
                dateOfBirth = LocalDate.of(1980, 1, 1),
                location = "World$testId",
            )
    }
}
