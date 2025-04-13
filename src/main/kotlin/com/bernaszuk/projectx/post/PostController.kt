package com.bernaszuk.projectx.post

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class PostController(
    val postService: PostService,
) {
    @PostMapping("/users/{userId}/posts")
    fun createPost(
        @PathVariable userId: UUID,
        @RequestBody @Valid postRequest: PostRequest,
    ): Post {
        val post = postService.createPost(userId, postRequest.content)
        return post
    }

    @GetMapping("/posts/{postId}")
    fun getPostById(
        @PathVariable("postId") postId: UUID,
    ): Post {
        val post = postService.getPostById(postId)
        return post
    }

    @GetMapping("/users/{userId}/posts")
    fun getAllPostsForUser(
        @PathVariable("userId") userId: UUID,
        @RequestParam page: Int = 0,
        @RequestParam size: Int = 5,
    ): PostsResponse {
        val post = postService.getAllPostsForUser(userId, page, size)
        return post
    }

    @PatchMapping("/users/{userId}/posts/{postId}")
    fun updatePost(
        @PathVariable("userId") userId: UUID,
        @PathVariable("postId") postId: UUID,
        @RequestBody updateRequest: PostRequest,
    ): Post {
        val post = postService.updatePost(userId, postId, updateRequest)
        return post
    }

    @DeleteMapping("/users/{userId}/posts/{postId}")
    fun deletePost(
        @PathVariable("userId") userId: UUID,
        @PathVariable("postId") postId: UUID,
    ): ResponseEntity<Unit> {
        postService.deletePost(userId, postId)
        return ResponseEntity.noContent().build()
    }
}
