package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.emums.UserRole
import com.example.exerciseexpert.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/student")
class StudentController: BaseController() {

    @Autowired
    lateinit var userRepository: UserRepository

    /**
     * Get the students, assigned to the teacher.
     * Currently all users with STUDENT role are considered assigned to any teacher.
     */
    @GetMapping
    fun getUsersStudents(model: Model): String {
        val students = userRepository.findByRole(UserRole.STUDENT)
        model.addAttribute("students", students)
        return "students"
    }

}