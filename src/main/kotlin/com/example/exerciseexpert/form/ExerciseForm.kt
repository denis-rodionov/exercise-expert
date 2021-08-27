package com.example.exerciseexpert.form

import javax.validation.constraints.NotBlank

data class ExerciseForm(
    @field:NotBlank(message = "name should not be empty")
    var name: String? = null,

    var new: Boolean = true,

    var id: String? = null
)