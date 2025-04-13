package com.bernaszuk.projectx

abstract class PagedResponse<T> {
    abstract val page: List<T>
    abstract val metadata: PaginationMetadata
}

data class PaginationMetadata(
    val pageNum: Int,
    val pageSize: Int,
    val totalPages: Int,
)
