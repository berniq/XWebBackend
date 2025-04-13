package com.bernaszuk.projectx.user

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository : JpaRepository<User, UUID> {
    fun findByUsername(name: String): User?

    override fun findAll(pageable: Pageable): Page<User>

    fun getUserById(id: UUID): User?
}
