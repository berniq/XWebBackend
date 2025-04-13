package com.bernaszuk.projectx.post

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "post")
class Post(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,
    var content: String,
    var userId: UUID,
)
