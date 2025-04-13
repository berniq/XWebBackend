package com.bernaszuk.projectx.user

class UserNotFoundExceptionByUserName(
    userName: String,
) : RuntimeException("User not found $userName")
