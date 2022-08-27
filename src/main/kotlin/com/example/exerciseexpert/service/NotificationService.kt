package com.example.exerciseexpert.service

import com.example.exerciseexpert.domain.AssignedExercise
import com.example.exerciseexpert.domain.Notification
import com.example.exerciseexpert.domain.emums.NotificationType
import com.example.exerciseexpert.repository.AssignedExerciseRepository
import com.example.exerciseexpert.repository.NotificationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class NotificationService {
    @Autowired
    lateinit var notificationRepository: NotificationRepository

    @Autowired
    lateinit var assignedExerciseRepository: AssignedExerciseRepository

    fun exerciseCompleted(assignedExercise: AssignedExercise, userName: String) {
        notificationRepository.save(Notification(
            null,
            NotificationType.EXERCISE_COMPLETED,
            assignedExercise.assignedByUserId,
            false,
            "User $userName has compleated the exercise ${assignedExercise.name} with score ${assignedExercise.resultScore}%",
            Instant.now(),
            null,
            assignedExerciseId = assignedExercise.id!!
        ))
    }

    fun exerciseCommented(assignedExerciseId: String, userName:String) {
        val assignedExercise = assignedExerciseRepository.findById(assignedExerciseId).orElseThrow {
            Exception("Assigned exercise was not found by id $assignedExerciseId")
        }

        notificationRepository.save(Notification(
            null,
            NotificationType.EXERCISE_COMMENTED,
            assignedExercise.assignedByUserId,
            false,
            "User $userName commented exercise ${assignedExercise.name}",
            Instant.now(),
            null,
            assignedExerciseId = assignedExercise.id!!
        ))
    }
}