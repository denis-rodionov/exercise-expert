package com.example.exerciseexpert.service

import com.example.exerciseexpert.domain.AssignedExercise
import com.example.exerciseexpert.domain.Notification
import com.example.exerciseexpert.domain.User
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

    fun exerciseCompleted(assignedExercise: AssignedExercise, user: User) {
        notificationRepository.save(Notification(
            null,
            NotificationType.EXERCISE_COMPLETED,
            assignedExercise.assignedByUserId,
            false,
            "User $user.name has compleated the exercise ${assignedExercise.name} with score ${assignedExercise.resultScore}%",
            Instant.now(),
            null,
            assignedExerciseId = assignedExercise.id!!,
            user.id
        ))
    }

    fun exerciseCommented(assignedExerciseId: String, user: User, messageId: String) {
        val assignedExercise = assignedExerciseRepository.findById(assignedExerciseId).orElseThrow {
            Exception("Assigned exercise was not found by id $assignedExerciseId")
        }

        // infer the notification recipient
        val notificationRecipientUserId =
            if (user.id!! == assignedExercise.assignedByUserId)
                assignedExercise.assignedToUserId
            else assignedExercise.assignedByUserId

        val notificationSourceUserId =
            if (user.id!! == assignedExercise.assignedByUserId)
                assignedExercise.assignedByUserId
            else assignedExercise.assignedToUserId

        notificationRepository.save(Notification(
            null,
            NotificationType.EXERCISE_COMMENTED,
            notificationRecipientUserId,
            false,
            "${user.name} commented exercise ${assignedExercise.name}",
            Instant.now(),
            messageId,
            assignedExerciseId = assignedExercise.id!!,
            notificationSourceUserId,
        ))
    }

    fun directMessageSent(authorUser: User, recipientUserId: String, messageId: String) {
        notificationRepository.save(Notification(
            null,
            NotificationType.DIRECT_MESSAGE,
            recipientUserId,
            false,
            "Direct message from ${authorUser.name}",
            Instant.now(),
            messageId,
            null,
            authorUser.id!!
        ))
    }
}