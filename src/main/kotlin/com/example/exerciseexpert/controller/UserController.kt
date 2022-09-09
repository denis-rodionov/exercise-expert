package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.emums.UserRole
import com.example.exerciseexpert.form.UserEditForm
import com.example.exerciseexpert.form.UserListItem
import com.example.exerciseexpert.repository.UserRepository
import com.example.exerciseexpert.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/user")
class UserController: BaseController() {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun showUserListPage(model: Model): String {
        val users = userRepository.findAll()
        users.forEach {
            logger.info("DEBUG: $it")
        }
        val teachers = users.map { it.id to it }.toMap()
        val viewUsers = users.map {
            UserListItem(
                it.id!!,
                it.name,
                it.role.RoleName,
                it.email,
                it.supervisorUserId,
                it.supervisorUserId?.let { teachers[it]?.name } .orEmpty()
            )
        }

        model.addAttribute("users", viewUsers)
        return "user-list"
    }

    @GetMapping("delete/{id}")
    fun deleteExercise(@PathVariable("id") userId: String): String {
        userRepository.deleteById(userId)
        return "redirect:/user"
    }

    @GetMapping("edit/{id}")
    fun showEditUserForm(@PathVariable("id") userId: String, model: Model): String {
        val user = userRepository.findById(userId).orElseThrow {
            Exception("User with id $userId not found")
        }
        val userForm = UserEditForm(user.id!!, user.name, user.email, "", user.role, user.supervisorUserId, null)

        model.addAttribute("user", userForm)
        model.addAttribute("supervisors", userService.getSupervisors())
        return "user-edit"
    }

    @PostMapping
    fun saveUser(@ModelAttribute user: UserEditForm): String {
        var domainUser = userRepository.findById(user.id).orElseThrow()
        logger.info("DEBUG: user role $user.role")
        domainUser.role = user.role?: throw Exception("No role set to the user")

        user.supervisorUserId?.let {
            if (it.isNotBlank()) {
                domainUser.supervisorUserId = it
            }
        }

        user.password?.let {
            if (it.isNotBlank()) {
                domainUser.userPassword = it
            }
        }
        userRepository.save(domainUser)
        return "redirect:/user"
    }
}