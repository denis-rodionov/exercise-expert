package com.example.exerciseexpert.repository

import com.example.exerciseexpert.domain.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository: CrudRepository<Message, String> {
    fun findByAssignedExerciseIdOrderByTimestampDesc(assignedExerciseId: String): List<Message>
}