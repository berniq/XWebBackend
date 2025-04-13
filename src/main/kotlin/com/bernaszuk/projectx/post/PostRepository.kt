package com.bernaszuk.projectx.post

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PostRepository : JpaRepository<Post, UUID> {
    fun findAllByUserId(
        userId: UUID,
        pageable: Pageable,
    ): Page<Post>

    fun getPostById(id: UUID): Post?
}
