package com.example.exerciseexpert.repository

import com.example.exerciseexpert.domain.Exercise

interface ExerciseRepository {
    fun getExercises(): List<Exercise>
}