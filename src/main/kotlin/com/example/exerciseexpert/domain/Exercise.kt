package com.example.exerciseexpert.domain

import org.springframework.data.annotation.Id

data class Exercise(
        @Id
        var id: String? = null,

        var name: String,

        var authorRef: UserName?,

        var author: String,

        var exerciseCode: String?
    )

