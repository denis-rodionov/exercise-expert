package com.example.exerciseexpert.repository

import com.example.exerciseexpert.domain.Exercise
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Repository

@Repository
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
class ExerciseRepositoryImpl : ExerciseRepository {

    val exerciseList = mutableListOf(
        Exercise(name = "Exercise 1 from repo"),
        Exercise(name = "Exercise 2 from repo")
    )

    override fun getExercises(): List<Exercise> {
        return exerciseList
    }

    override fun createExercise(newExercise: Exercise) {
        exerciseList.add(newExercise)
    }
}