package com.example.exerciseexpert.domain

import org.springframework.data.annotation.Id

// can be used for references
data class UserName (
    @Id
    var id: String? = null,

    var userName: String,

    var userId: String
)