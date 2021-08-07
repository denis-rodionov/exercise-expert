package com.example.exerciseexpert.form

import javax.validation.constraints.NotBlank

class LoginData(
    @field:NotBlank(message = "username should not be empty")
    var username: String? = null
)