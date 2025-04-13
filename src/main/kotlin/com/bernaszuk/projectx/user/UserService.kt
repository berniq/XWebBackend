package com.bernaszuk.projectx.user

import com.bernaszuk.projectx.PaginationMetadata
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    val userRepository: UserRepository,
) {
    fun findUsers(
        page: Int,
        size: Int,
    ): UserResponse {
        val pageable: Pageable = PageRequest.of(page, size)
        val users = userRepository.findAll(pageable)
        val getUserRespond =
            UserResponse(
                page = users.content,
                metadata =
                    PaginationMetadata(
                        pageNum = users.pageable.pageNumber,
                        pageSize = users.pageable.pageSize,
                        totalPages = users.totalPages,
                    ),
            )
        return getUserRespond
    }

    fun findUserByUserName(userName: String): User =
        userRepository.findByUsername(userName) ?: throw UserNotFoundExceptionByUserName(userName)

    fun getUserById(userId: UUID): User = userRepository.getUserById(userId) ?: throw UserNotFoundExceptionById(userId)

    fun updateUser(user: User): User = userRepository.save(user)

    fun createUser(request: CreateUserRequest): User {
        val user =
            User(
                username = request.username,
                handle = request.handle,
                firstName = request.firstName,
                lastName = request.lastName,
                email = request.email,
                dateOfBirth = request.dateOfBirth,
                location = request.location,
                bio = null,
            )
        return userRepository.save(user)
    }

    fun deleteUser(userId: UUID) {
        val user = getUserById(userId)
        userRepository.delete(user)
    }
}
