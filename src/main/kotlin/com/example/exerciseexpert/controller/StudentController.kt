package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.emums.UserRole
import com.example.exerciseexpert.repository.AssignedExerciseRepository
import com.example.exerciseexpert.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.lang.Exception

@Controller
@RequestMapping("/student")
class StudentController: BaseController() {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var assignedExercisesRepository: AssignedExerciseRepository

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

    @GetMapping("{id}")
    fun getStudent(@PathVariable("id") userId: String, model: Model): String {
        val student = userRepository.findById(userId).orElseThrow {
            Exception("Student not found by the user id $userId")
        }
        val assignedExercise = assignedExercisesRepository.findByAssignedToUserId(student.id!!)
        model.addAttribute("student", student)
        model.addAttribute("assignedExercise", assignedExercise)
        return "teacher-student-view"
    }
}