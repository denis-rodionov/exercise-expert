package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.AssignedExercise
import com.example.exerciseexpert.repository.AssignedExerciseRepository
import com.example.exerciseexpert.repository.ExerciseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.time.Instant

@Controller
@RequestMapping("/select-exercise")
class SelectExerciseController: BaseController() {
    @Autowired
    lateinit var exerciseRepository: ExerciseRepository

    @Autowired
    lateinit var assignedExercisesRepository: AssignedExerciseRepository

    @GetMapping("{userId}")
    fun showSelectExerciseView(@PathVariable userId: String, model: Model): String {
        val allExercises = exerciseRepository.findAll()
        model.addAttribute("exercises", allExercises)
        model.addAttribute("userId", userId)
        return "select-exercises"
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun returnSelectedExercise(@RequestParam userId: String, @RequestParam exerciseId: String) {
        println("Assigning exercise to a user")

        val exercise = exerciseRepository.findById(exerciseId).orElseThrow {
            Exception("No exercise with id $exerciseId found!")
        }

        assignedExercisesRepository.save(AssignedExercise(
            null,
            exerciseId = exercise.id!!,
            name = exercise.name,
            assignedToUserId = userId,
            assignedByUserId = userContext().user!!.id.orEmpty(),
            assignedAt = Instant.now(),
            result = null,
            resultShort = null,
            resultScore = null,
            reviewComment = null,
            reviewedByUserId = null,
            doneAt = null,
        ))
    }
}