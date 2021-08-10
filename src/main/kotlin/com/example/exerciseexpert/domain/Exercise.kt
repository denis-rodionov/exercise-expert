package com.example.exerciseexpert.domain

import org.springframework.data.annotation.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class Exercise(
        @Id
        var id: String? = null,

        @NotNull
        @Size(min=5, message="Name must be at least 5 characters long")
        @field:NotBlank(message = "name should not be empty")
        var name: String? = null,

        var author: String? = null
    )

