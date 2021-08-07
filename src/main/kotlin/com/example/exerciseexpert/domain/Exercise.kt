package com.example.exerciseexpert.domain

import javax.validation.constraints.NotBlank

class Exercise(
    @field:NotBlank(message = "name should not be empty")
    var name: String? = null,

    var author: String? = null
)