package com.example.exerciseexpert.domain

import org.springframework.data.annotation.Id
import java.time.Instant

data class AssignedExercise(
    @Id
    var id: String? = null,

    var name: String,

    var exerciseId: String,

    var assignedToUserId: String,

    var assignedByUserId: String,

    // time, when the exercise was assigned to the user
    var assignedAt: Instant,

    // the result HTML code, showing, how the user have done the exercise
    var result: String?,

    var reviewedByUserId: String?,

    var reviewCommend: String?
)