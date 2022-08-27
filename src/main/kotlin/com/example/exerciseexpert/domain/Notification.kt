package com.example.exerciseexpert.domain

import com.example.exerciseexpert.domain.emums.NotificationType
import org.springframework.data.annotation.Id
import java.time.Instant

data class Notification (
    @Id
    var id: String? = null,

    var type: NotificationType,

    // the user, which should be notified
    var userId: String,

    // if the notification have been seen
    var viewed: Boolean = false,

    // notification text
    var text: String? = null,

    var timestamp: Instant,

    // in case the notification is about new message
    var messageId: String? = null,

    var assignedExerciseId: String? = null,
)
