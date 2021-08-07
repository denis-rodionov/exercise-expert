package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.User
import com.example.exerciseexpert.domain.UserContext
import com.example.exerciseexpert.form.LoginData
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/auth")
class AuthController : BaseController() {
    @GetMapping
    fun loginPage(@ModelAttribute userContext: UserContext, model: Model): String {
        if (userContext.user != null) {
            return "redirect:/"
        }
        model.addAttribute("loginData", LoginData())
        return "login"
    }

    @PostMapping
    fun login(@ModelAttribute userContext: UserContext, model: Model,
              @Valid @ModelAttribute loginData: LoginData,
              errors: Errors): String {
        if (errors.hasErrors()) {
            return "login"
        }
        userContext.user = User(loginData.username.orEmpty())
        return "redirect:/"
    }

    @GetMapping("/logout")
    fun logout(@ModelAttribute userContext: UserContext): String {
        userContext.user = null
        return "redirect:/"
    }
}