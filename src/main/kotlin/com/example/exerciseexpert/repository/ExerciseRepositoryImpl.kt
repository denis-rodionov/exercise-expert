package com.example.exerciseexpert.repository

import com.example.exerciseexpert.domain.Exercise

class ExerciseRepositoryImpl : ExerciseRepository {
    override fun getExercises(): List<Exercise> {
        return listOf(
            Exercise(name = "Exercise 1"),
            Exercise(name = "Exercise 2")
        )
    }
}