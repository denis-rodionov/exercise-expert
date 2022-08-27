package com.example.exerciseexpert.domain

import org.springframework.data.annotation.Id

data class Notification (
    @Id
    var id: String? = null,

    // the user, which should be notified
    var userId: String,

    // if the notification have been seen
    var viewed: Boolean = false,

    // notification text
    var text: String? = null,

    // in case the notification is about new message
    var messageId: String? = null,
)
