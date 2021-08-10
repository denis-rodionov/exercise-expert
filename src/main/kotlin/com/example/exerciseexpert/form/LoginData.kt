package com.example.exerciseexpert.form

import javax.validation.constraints.NotBlank

class LoginData(
    @field:NotBlank(message = "name should not be empty")
    var email: String? = null,

    @field:NotBlank(message = "password should not be empty")
    var password: String? = null
)