package com.bernaszuk.projectx.post

import java.util.UUID

class PostTestDataFactory {
    companion object {
        fun createTestPost(
            testId: Int = 1,
            userId: UUID,
        ) = Post(
            content = "test content$testId",
            userId = userId,
        )

        fun createTestPost(testId: Int = 1) =
            createTestPost(
                testId = testId,
                userId = UUID.randomUUID(),
            )
    }
}
