package com.example.exerciseexpert.repository

import com.example.exerciseexpert.domain.AssignedExercise
import org.springframework.data.repository.PagingAndSortingRepository

interface AssignedExerciseRepository : PagingAndSortingRepository<AssignedExercise, String> {
    fun findByAssignedToUserId(userId: String): List<AssignedExercise>
}