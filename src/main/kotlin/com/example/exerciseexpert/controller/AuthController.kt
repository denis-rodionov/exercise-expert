package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.User
import com.example.exerciseexpert.domain.emums.UserRole
import com.example.exerciseexpert.form.RegisterData
import com.example.exerciseexpert.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.time.Instant
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid


@Controller
@RequestMapping("/auth")
class AuthController : BaseController() {

    @Autowired
    lateinit var userRepository: UserRepository

    @GetMapping("/logout")
    fun logout(request: HttpServletRequest, response: HttpServletResponse): String {
        val authentication: Authentication? = SecurityContextHolder.getContext().authentication
        if (authentication != null) {
            SecurityContextLogoutHandler().logout(request, response, authentication)
        }

        return "redirect:/login"
    }

    @GetMapping("/register")
    fun registerPage(model: Model): String {
        val admins = userRepository.findByRole(UserRole.ADMIN)
        val teachers = userRepository.findByRole(UserRole.TEACHER)

        model.addAttribute("registerData", RegisterData())
        model.addAttribute("supervisors", admins + teachers)
        return "register"
    }

    @PostMapping("/register")
    fun register(@ModelAttribute(name = "registerData") @Valid registerData: RegisterData, errors: Errors, model: Model): String {
        if (errors.hasErrors()) {
            return "register"
        }
        if (registerData.password != registerData.confirmPassword) {
            errors.fieldErrors += FieldError("registerData", "confirmPassword", "password does not match")
            return "register"
        }

        userRepository.save(User(
            null,
            registerData.name!!,
            registerData.email!!,
            registerData.password!!,
            UserRole.STUDENT,
            registerData.supervisorUserId,
            Instant.now(),
        ))
        logger.info("New user is saved: ${registerData.name}")
        return "redirect:/login"
    }
}