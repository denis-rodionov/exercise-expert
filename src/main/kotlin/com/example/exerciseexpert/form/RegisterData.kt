package com.example.exerciseexpert.form

import javax.validation.constraints.NotBlank

data class RegisterData(
    @field:NotBlank(message = "name should not be empty")
    var name: String? = null,

    @field:NotBlank(message = "name should not be empty")
    var email: String? = null,

    @field:NotBlank(message = "password should not be empty")
    var password: String? = null,

    @field:NotBlank(message = "password confirmation should not be empty")
    var confirmPassword: String? = null,

    @field:NotBlank(message = "please choose supervisor")
    var supervisorUserId: String? = null,
)