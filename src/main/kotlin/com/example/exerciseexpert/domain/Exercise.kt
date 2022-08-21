package com.example.exerciseexpert.domain

import org.springframework.data.annotation.Id
import java.time.Instant
import java.time.ZonedDateTime

data class Exercise(
        @Id
        var id: String? = null,

        var name: String,

        var authorRef: UserName?,

        // the author name, to avoid additional call to the database
        var author: String,

        // HTML code for the exercise
        var exerciseCode: String,

        var createdAt: Instant,

        var updatedAt: Instant,
    )

