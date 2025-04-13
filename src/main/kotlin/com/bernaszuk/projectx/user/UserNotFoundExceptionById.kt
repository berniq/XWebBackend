package com.bernaszuk.projectx.user

import java.util.UUID

class UserNotFoundExceptionById(
    userId: UUID,
) : RuntimeException("User not found $userId")
