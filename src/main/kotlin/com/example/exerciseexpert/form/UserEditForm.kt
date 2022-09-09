package com.example.exerciseexpert.form

import com.example.exerciseexpert.domain.emums.UserRole
import javax.validation.constraints.NotBlank

data class UserEditForm(
    var id: String,

    var name: String,

    var email: String,

    var password: String? = null,

    @field:NotBlank(message = "user role should not be empty")
    var role: UserRole? = null,

    var supervisorUserId: String? = null,

    val avatarReadonly: String?,
)