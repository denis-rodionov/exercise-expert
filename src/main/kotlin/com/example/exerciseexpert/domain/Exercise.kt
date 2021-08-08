package com.example.exerciseexpert.domain

import org.springframework.data.annotation.Id
import javax.validation.constraints.NotBlank

data class Exercise(
        @Id
        var id: String? = null,

        @field:NotBlank(message = "name should not be empty")
        var name: String? = null,

        var author: String? = null
    )

