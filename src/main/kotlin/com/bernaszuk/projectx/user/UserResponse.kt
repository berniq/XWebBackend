package com.bernaszuk.projectx.user

import com.bernaszuk.projectx.PagedResponse
import com.bernaszuk.projectx.PaginationMetadata

data class UserResponse(
    override val page: List<User>,
    override val metadata: PaginationMetadata,
) : PagedResponse<User>()
