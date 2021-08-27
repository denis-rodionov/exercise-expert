package com.example.exerciseexpert.controller

import com.example.exerciseexpert.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Repository
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/user")
class UserController: BaseController() {

    @Autowired
    lateinit var userRepository: UserRepository

    @GetMapping
    fun showUserListPage(model: Model): String {
        val users = userRepository.findAll()
        model.addAttribute("users", users)
        return "user-list"
    }

    @GetMapping("delete/{id}")
    fun deleteExercise(@PathVariable("id") userId: String): String {
        logger.info("DEBUG: deleting user with ID=$userId")
        userRepository.deleteById(userId)
        return "redirect:/user"
    }
}