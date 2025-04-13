package com.bernaszuk.projectx.post

import com.bernaszuk.projectx.post.PostTestDataFactory.Companion.createTestPost
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.refEq
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class PostServiceTest {
    private val postRepository: PostRepository = mock(PostRepository::class.java)
    private val postService = PostService(postRepository)

    @Test
    fun createsPostWithProvidedContent() {
        // Given
        val expectedPost = createTestPost()

        // Mock what happens when postService tries to invoke postRepository
        `when`(postRepository.save(refEq(expectedPost))).thenReturn(expectedPost)

        // When
        val actualPost = postService.createPost(expectedPost.userId, expectedPost.content)

        // Then
        assertThat(actualPost).isEqualTo(expectedPost)
    }

    @Test
    fun throwsExceptionWhenFailedToPersistPostInDB() {
        // Given
        val expectedPost = createTestPost()

        // Mock what happens when postService tries to invoke postRepository
        `when`(postRepository.save(refEq(expectedPost))).thenThrow(RuntimeException("Failed to persist post"))

        // Then
        assertThrows<RuntimeException> { postService.createPost(expectedPost.userId, expectedPost.content) }
    }
}
