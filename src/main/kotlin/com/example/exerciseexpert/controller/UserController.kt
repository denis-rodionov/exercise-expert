package com.example.exerciseexpert.controller

import com.example.exerciseexpert.form.UserEditForm
import com.example.exerciseexpert.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/user")
class UserController: BaseController() {

    @Autowired
    lateinit var userRepository: UserRepository

    @GetMapping
    fun showUserListPage(model: Model): String {
        val users = userRepository.findAll()
        users.forEach {
            logger.info("DEBUG: $it")
        }
        model.addAttribute("users", users)
        return "user-list"
    }

    @GetMapping("delete/{id}")
    fun deleteExercise(@PathVariable("id") userId: String): String {
        logger.info("DEBUG: deleting user with ID=$userId")
        userRepository.deleteById(userId)
        return "redirect:/user"
    }

    @GetMapping("edit/{id}")
    fun showEditUserForm(@PathVariable("id") userId: String, model: Model): String {
        val user = userRepository.findById(userId).orElseThrow {
            Exception("User with id $userId not found")
        }
        val userForm = UserEditForm(user.id!!, user.name, user.email, "", user.role)
        model.addAttribute("user", userForm)
        return "user-edit"
    }

    @PostMapping
    fun saveUser(@ModelAttribute user: UserEditForm): String {
        var domainUser = userRepository.findById(user.id).orElseThrow()
        logger.info("DEBUG: user role $user.role")
        domainUser.role = user.role
        user.password?.let {
            if (it.isNotBlank()) {
                domainUser.userPassword = it
            }
        }
        userRepository.save(domainUser)
        return "redirect:/user"
    }
}