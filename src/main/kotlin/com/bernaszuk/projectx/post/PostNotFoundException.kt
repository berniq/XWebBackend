package com.bernaszuk.projectx.post

import java.util.UUID

class PostNotFoundException(
    postId: UUID,
) : RuntimeException("Post not found $postId")
