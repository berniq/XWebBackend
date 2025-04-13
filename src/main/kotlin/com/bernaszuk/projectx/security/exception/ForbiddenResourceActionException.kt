package com.bernaszuk.projectx.security.exception

import java.util.UUID

class ForbiddenResourceActionException(
    userId: UUID,
) : RuntimeException("User with id: $userId does not have access to this resource.")
