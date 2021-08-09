package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.UserContext
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
        return "home"
    }
}