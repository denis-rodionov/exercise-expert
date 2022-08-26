package com.example.exerciseexpert.controller

import com.example.exerciseexpert.repository.AssignedExerciseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/dashboard")
class StudentDashboard: BaseController() {
    @Autowired
    lateinit var assignedExerciseRepository: AssignedExerciseRepository

    @GetMapping
    fun getDashboard(model: Model): String {
        val studentUser = user()
        logger.info("Student dashboard loading: $studentUser")
        val assignedExercises = assignedExerciseRepository.findByAssignedToUserId(studentUser.id!!)
        model.addAttribute("assignedExercises", assignedExercises)
        return "student-dashboard"
    }
}