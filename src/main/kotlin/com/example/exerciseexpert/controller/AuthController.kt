package com.example.exerciseexpert.controller

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Controller
@RequestMapping("/auth")
class AuthController : BaseController() {

    @GetMapping("/logout")
    fun logout(request: HttpServletRequest, response: HttpServletResponse): String {
        val authentication: Authentication? = SecurityContextHolder.getContext().authentication
        if (authentication != null) {
            SecurityContextLogoutHandler().logout(request, response, authentication)
        }

        return "redirect:/login"
    }
}