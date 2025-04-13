package com.bernaszuk.projectx

import com.bernaszuk.projectx.post.PostRepository
import com.bernaszuk.projectx.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
abstract class AbstractIntegrationTest {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var postRepository: PostRepository

    @Autowired
    lateinit var webTestClient: WebTestClient

    companion object {
        @Container
        @JvmStatic
        val POSTGRES_CONTAINER: PostgreSQLContainer<*> =
            PostgreSQLContainer("postgres:latest")
                .withDatabaseName("postgres")
                .withUsername("pg-test-username")
                .withPassword("pg-test-username")

        @DynamicPropertySource
        @JvmStatic
        fun dynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { "jdbc:postgresql://localhost:${POSTGRES_CONTAINER.firstMappedPort}/postgres" }
        }
    }
}
