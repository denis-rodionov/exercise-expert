package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.emums.UserRole
import com.example.exerciseexpert.repository.AssignedExerciseRepository
import com.example.exerciseexpert.repository.MessageRepository
import com.example.exerciseexpert.repository.UserRepository
import com.example.exerciseexpert.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.lang.Exception

@Controller
@RequestMapping("/student")
class StudentController: BaseController() {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var assignedExercisesRepository: AssignedExerciseRepository

    @Autowired
    lateinit var messageService: MessageService

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
    fun showStudentView(@PathVariable("id") userId: String, model: Model): String {
        val student = userRepository.findById(userId).orElseThrow {
            Exception("Student not found by the user id $userId")
        }
        val assignedExercises = assignedExercisesRepository.findByAssignedToUserId(student.id!!)
        val messages = messageService.getDirectMessagesBetweenTwoUsers(student.id!!, user().id!!)
        val doneExercises = assignedExercises.filter { it.result != null }
        val exerciseProgressPercent =  doneExercises.size * 100 / assignedExercises.size

        model.addAttribute("student", student)
        model.addAttribute("assignedExercises", assignedExercises)
        model.addAttribute("messages", messages)
        model.addAttribute("exerciseProgressPercent", exerciseProgressPercent)
        return "teacher-student-view"
    }

    @DeleteMapping("{studentId}/exercise/{assignedExerciseId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun deleteAssignedExercise(@PathVariable studentId: String, @PathVariable assignedExerciseId: String) {
        println("Deleteing the exercise....")
        val assignedExercise = assignedExercisesRepository.findById(assignedExerciseId).orElseThrow { Exception("Assigned exercise not found") }
        assignedExercisesRepository.delete(assignedExercise)
    }
}