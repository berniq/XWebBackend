package com.bernaszuk.projectx.post

import com.bernaszuk.projectx.AbstractIntegrationTest
import com.bernaszuk.projectx.post.PostTestDataFactory.Companion.createTestPost
import com.bernaszuk.projectx.user.UserTestDataFactory.Companion.createTestUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.util.UUID

class PostControllerIT : AbstractIntegrationTest() {
    @AfterEach
    fun cleanUp() {
        postRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    fun `Get existing post by id`() {
        val user = userRepository.save(createTestUser())
        val post = postRepository.save(createTestPost(1, user.id!!))

        val checkedPost =
            webTestClient
                .get()
                .uri("/posts/${post.id}")
                .exchange()
                .expectStatus()
                .isOk
                .expectBody(Post::class.java)
                .returnResult()
                .responseBody!!

        assertThat(checkedPost.content)
            .isEqualTo(post.content)
    }

    @Test
    fun `Gives error message when requested post does not exist`() {
        val postId = UUID.randomUUID()

        webTestClient
            .get()
            .uri("/posts/$postId")
            .exchange()
            .expectStatus()
            .isNotFound
            .expectBody()
    }

    @Test
    fun `Creates post for given user`() {
        val user = userRepository.save(createTestUser())

        val postRequest =
            PostRequest(
                content = "testPost",
            )

        val updatedPost =
            webTestClient
                .post()
                .uri("/users/${user.id}/posts")
                .bodyValue(postRequest)
                .exchange()
                .expectStatus()
                .isOk
                .expectBody(Post::class.java)
                .returnResult()
                .responseBody!!

        assertThat(updatedPost.userId).isEqualTo(user.id)
        assertThat(updatedPost.content).isEqualTo(postRequest.content)
    }

    @Test
    fun `Allows to edit posts by owner`() {
        val owner = userRepository.save(createTestUser())
        val postToUpdate = postRepository.save(createTestPost(userId = owner.id!!))

        val postUpdateRequest =
            PostRequest(
                content = "updatedPostContent",
            )

        val updatedPost =
            webTestClient
                .patch()
                .uri("/users/${owner.id}/posts/${postToUpdate.id}")
                .bodyValue(postUpdateRequest)
                .exchange()
                .expectStatus()
                .isOk
                .expectBody(Post::class.java)
                .returnResult()
                .responseBody!!

        assertThat(updatedPost.content)
            .isEqualTo(postUpdateRequest.content)
    }

    @Test
    fun `Does not allow to edit other user's posts`() {
        val owner = userRepository.save(createTestUser(1))
        val postToUpdate = postRepository.save(createTestPost(1, owner.id!!))

        val otherUser = userRepository.save(createTestUser(2))

        val testPostRequest =
            PostRequest(
                content = "updatedPostContent",
            )

        webTestClient
            .patch()
            .uri("/users/${otherUser.id}/posts/${postToUpdate.id}")
            .bodyValue(testPostRequest)
            .exchange()
            .expectStatus()
            .isForbidden
            .expectBody()
    }

    @Test
    fun `Allows to delete posts by owner`() {
        val owner = userRepository.save(createTestUser())
        val postToDelete = postRepository.save(createTestPost(userId = owner.id!!))

        webTestClient
            .delete()
            .uri("/users/${owner.id}/posts/${postToDelete.id}")
            .exchange()
            .expectStatus()
            .isNoContent
            .expectBody()
    }

    @Test
    fun `Does not allow to delete other's users posts`() {
        val owner = userRepository.save(createTestUser(1))
        val postToDelete = postRepository.save(createTestPost(1, owner.id!!))

        val otherUser = userRepository.save(createTestUser(2))

        webTestClient
            .delete()
            .uri("/users/${otherUser.id}/posts/${postToDelete.id}")
            .exchange()
            .expectStatus()
            .isForbidden
            .expectBody()
    }

    @Test
    fun `Supports pagination when requesting for posts of a given user`() {
        val owner = userRepository.save(createTestUser())
        (1..12).map { postRepository.save(createTestPost(it, owner.id!!)) }

        val firstPage =
            webTestClient
                .get()
                .uri("/users/${owner.id}/posts")
                .exchange()
                .expectStatus()
                .isOk
                .expectBody(PostsResponse::class.java)
                .returnResult()
                .responseBody!!

        assertThat(firstPage.page).hasSize(5)
        assertThat(firstPage.metadata.pageNum).isEqualTo(0)
        assertThat(firstPage.metadata.pageSize).isEqualTo(5)
        assertThat(firstPage.metadata.totalPages).isEqualTo(3)

        val secondPage =
            webTestClient
                .get()
                .uri("/users/${owner.id}/posts?page=1")
                .exchange()
                .expectStatus()
                .isOk
                .expectBody(PostsResponse::class.java)
                .returnResult()
                .responseBody!!

        assertThat(secondPage.page).hasSize(5)
        assertThat(secondPage.metadata.pageNum).isEqualTo(1)
        assertThat(secondPage.metadata.pageSize).isEqualTo(5)
        assertThat(secondPage.metadata.totalPages).isEqualTo(3)

        val lastPage =
            webTestClient
                .get()
                .uri("/users/${owner.id}/posts?page=2")
                .exchange()
                .expectStatus()
                .isOk
                .expectBody(PostsResponse::class.java)
                .returnResult()
                .responseBody!!

        assertThat(lastPage.page).hasSize(2)
        assertThat(lastPage.metadata.pageNum).isEqualTo(2)
        assertThat(lastPage.metadata.pageSize).isEqualTo(5)
        assertThat(lastPage.metadata.totalPages).isEqualTo(3)
    }
}
