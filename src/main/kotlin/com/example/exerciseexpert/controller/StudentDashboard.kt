package com.example.exerciseexpert.controller

import com.example.exerciseexpert.repository.AssignedExerciseRepository
import com.example.exerciseexpert.service.MessageService
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

    @Autowired
    lateinit var messageService: MessageService

    @GetMapping
    fun getDashboard(model: Model): String {
        val studentUser = user()
        logger.info("Student dashboard loading: $studentUser")
        val assignedExercises = assignedExerciseRepository.findByAssignedToUserId(studentUser.id!!)
        val messages = messageService.getMyDirectMessages(studentUser.id!!)

        model.addAttribute("assignedExercises", assignedExercises)
        model.addAttribute("messages", messages)
        return "student-dashboard"
    }
}