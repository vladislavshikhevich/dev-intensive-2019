package ru.skillbranch.dev_intensive.models

class UserView(
    val id: String,
    val fullName: String,
    val nickName: String,
    val avatart: String? = null,
    val status: String? = "offline",
    val initials: String?
)