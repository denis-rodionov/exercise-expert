package com.example.exerciseexpert.repository

import com.example.exerciseexpert.domain.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository: CrudRepository<Message, String> {
    fun findByAssignedExerciseIdOrderByTimestampAsc(assignedExerciseId: String): List<Message>
    fun findByRecipientIdAndAuthorIdOrderByTimestampAsc(recipientId: String, authorId: String): List<Message>
    fun findByAuthorIdAndAssignedExerciseIdOrderByTimestampAsc(authorId: String, assignedExerciseId: String?): List<Message>
    fun findByRecipientIdAndAssignedExerciseIdOrderByTimestampAsc(recipientId: String, assignedExerciseId: String?): List<Message>
}