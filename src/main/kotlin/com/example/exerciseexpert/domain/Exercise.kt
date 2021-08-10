package com.example.exerciseexpert.domain

import org.springframework.data.annotation.Id
import javax.validation.constraints.NotBlank

data class Exercise(
        @Id
        var id: String? = null,

        var name: String,

        var author: String
    )

