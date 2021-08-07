package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.UserContext
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.SessionAttributes

@SessionAttributes("userContext")
open class BaseController {
    val logger = LoggerFactory.getLogger(BaseController::class.java)

    @ModelAttribute(name = "userContext")
    fun user(): UserContext {
        logger.info("Getting user info...")
        return UserContext(null)
    }
}