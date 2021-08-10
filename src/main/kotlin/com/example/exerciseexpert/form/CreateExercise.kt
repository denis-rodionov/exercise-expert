package com.example.exerciseexpert.form

import javax.validation.constraints.NotBlank

data class CreateExercise(
    @field:NotBlank(message = "name should not be empty")
    var name: String? = null,
)