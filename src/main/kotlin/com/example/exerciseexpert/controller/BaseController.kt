package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.User
import com.example.exerciseexpert.domain.UserContext
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.ModelAttribute

open class BaseController {
    val logger = LoggerFactory.getLogger(BaseController::class.java)

    @ModelAttribute(name = "userContext")
    fun userContext(): UserContext {
        val user = SecurityContextHolder.getContext().authentication.principal;
        return if (user is User) UserContext(user) else UserContext(null)
    }

    fun user(): User {
        return userContext().user ?: throw Exception("Could not find a use in the context!")
    }
}