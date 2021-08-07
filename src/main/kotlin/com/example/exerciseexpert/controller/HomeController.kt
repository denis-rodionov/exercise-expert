package com.example.exerciseexpert.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping
class HomeController : BaseController() {
    @GetMapping
    fun index(model: Model) : String {
        return "home"
    }
}