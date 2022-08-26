package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.UserContext
import com.example.exerciseexpert.domain.emums.UserRole
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping
class HomeController : BaseController() {
    @GetMapping
    fun index(@ModelAttribute userContext: UserContext, model: Model) : String {
        val loggedUser = user()
        println("User ${loggedUser.name}")

        return when (loggedUser.role) {
            UserRole.STUDENT -> "redirect:/dashboard"
            UserRole.ADMIN -> "redirect:/user"
            UserRole.TEACHER -> "redirect:/student"
            else -> {
                throw Exception("Unknown user role: $loggedUser.role")
            }
        }
    }
}