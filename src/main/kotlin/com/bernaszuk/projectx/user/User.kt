package com.bernaszuk.projectx.user

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "\"customer\"")
class User(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,
    var handle: String,
    var username: String,
    var bio: String?,
    var firstName: String,
    var lastName: String,
    var email: String,
    var dateOfBirth: LocalDate,
    var location: String,
)
