package com.example.exerciseexpert.repository

import com.example.exerciseexpert.domain.Exercise

class ExerciseRepositoryImpl : ExerciseRepository {
    override fun getExercises(): List<Exercise> {
        return listOf(
            Exercise(id = 1, name = "Exercise 1"),
            Exercise(id = 2, name = "Exercise 2")
        )
    }
}