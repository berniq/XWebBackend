package com.bernaszuk.projectx.post

import com.bernaszuk.projectx.PaginationMetadata
import com.bernaszuk.projectx.security.exception.ForbiddenResourceActionException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PostService(
    val postRepository: PostRepository,
) {
    fun createPost(
        userId: UUID,
        content: String,
    ): Post {
        val post = Post(content = content, userId = userId)
        return postRepository.save(post)
    }

    fun getPostById(id: UUID): Post {
        val post = postRepository.getPostById(id) ?: throw PostNotFoundException(id)
        return post
    }

    fun getAllPostsForUser(
        userId: UUID,
        page: Int,
        size: Int,
    ): PostsResponse {
        val pageable: Pageable = PageRequest.of(page, size)
        val posts = postRepository.findAllByUserId(userId, pageable)

        val getPostRespond =
            PostsResponse(
                page = posts.content,
                metadata =
                    PaginationMetadata(
                        pageNum = posts.pageable.pageNumber,
                        pageSize = posts.pageable.pageSize,
                        totalPages = posts.totalPages,
                    ),
            )
        return getPostRespond
    }

    fun updatePost(
        requestingUserId: UUID,
        postId: UUID,
        updatedPost: PostRequest,
    ): Post {
        val post = getPostById(postId)

        if (post.userId != requestingUserId) {
            throw ForbiddenResourceActionException(requestingUserId)
        }

        post.content = updatedPost.content
        return postRepository.save(post)
    }

    fun deletePost(
        requestingUserId: UUID,
        postId: UUID,
    ) {
        val post = getPostById(postId)

        if (post.userId != requestingUserId) {
            throw ForbiddenResourceActionException(requestingUserId)
        }

        postRepository.delete(post)
    }
}
