package com.example.exerciseexpert.domain

import org.springframework.data.annotation.Id
import java.time.Instant

data class Message (
    @Id
    var id: String? = null,

    var authorId: String,

    var authorName: String?,

    // User, which should receive the message. Can be null, if the message is used as a comment.
    var recipientId: String?,

    // Exercise, which the message comments. Can be null in case of the direct message
    var assignedExerciseId: String?,

    var message: String,

    var timestamp: Instant,
)