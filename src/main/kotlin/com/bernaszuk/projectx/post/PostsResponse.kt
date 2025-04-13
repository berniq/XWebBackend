package com.bernaszuk.projectx.post

import com.bernaszuk.projectx.PagedResponse
import com.bernaszuk.projectx.PaginationMetadata

data class PostsResponse(
    override val page: List<Post>,
    override val metadata: PaginationMetadata,
) : PagedResponse<Post>()
