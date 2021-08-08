package com.example.exerciseexpert.repository

import com.example.exerciseexpert.domain.Exercise
import org.springframework.data.repository.PagingAndSortingRepository

interface ExerciseRepository : PagingAndSortingRepository<Exercise, String> {
}