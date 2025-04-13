package com.bernaszuk.projectx.post

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class PostRequest(
    @field:NotBlank
    @field:Size(max = 1024)
    val content: String,
)
