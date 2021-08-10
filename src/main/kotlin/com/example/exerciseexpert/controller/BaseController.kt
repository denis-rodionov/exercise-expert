package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.User
import com.example.exerciseexpert.domain.UserContext
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.ModelAttribute

open class BaseController {
    val logger = LoggerFactory.getLogger(BaseController::class.java)

    @ModelAttribute(name = "userContext")
    fun user(): UserContext {
        val user = SecurityContextHolder.getContext().authentication.principal;
        logger.info("Logged user: $user")
        if (user is User) {
            return UserContext(user)
        } else {
            return UserContext(null)
        }
    }
}