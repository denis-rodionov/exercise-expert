package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.Exercise
import com.example.exerciseexpert.domain.User
import com.example.exerciseexpert.domain.UserContext
import com.example.exerciseexpert.domain.UserName
import com.example.exerciseexpert.form.ExerciseForm
import com.example.exerciseexpert.repository.ExerciseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@Controller
@RequestMapping("/exercise")
class ExerciseListController : BaseController() {
    @Autowired
    lateinit var exerciseRepository: ExerciseRepository

    @GetMapping
    fun getAllExercises(@ModelAttribute userContext: UserContext, model: Model): String {
        val allExercises = exerciseRepository.findAll()
        model.addAttribute("exercises", allExercises)
        return "exercise-list"
    }

    @GetMapping("/create")
    fun createExerciseForm(@ModelAttribute userContext: UserContext, model: Model): String {
        model.addAttribute("exercise", ExerciseForm())
        return "exercise-view"
    }

    @PostMapping
    fun saveExercise(@ModelAttribute(name = "exercise") @Valid exercise: ExerciseForm,
                       errors: Errors,
                       model: Model,
                       @ModelAttribute userContext: UserContext,): String {
        logger.info("DEBUG: save exercise $exercise")
        if (errors.hasErrors()) {
            return "exercise-view"
        }
        logger.info("User ${userContext.user} saving exercise...")

        val author: User =  userContext.user ?: throw Exception("User not found")

        if (exercise.new) {
            val newExercise = Exercise(null, exercise.name!!,
                UserName(null, author.name, author.id!!), author.name)
            exerciseRepository.save(newExercise)
        } else {
            var exerciseId = exercise.id ?: throw Exception("Exercise ID not found")
            val existingExercise = exerciseRepository.findById(exerciseId)
                .orElseThrow()
            logger.info(existingExercise.toString())
            existingExercise.name = exercise.name!!
            exerciseRepository.save(existingExercise)
        }

        return "redirect:/exercise"
    }

    @GetMapping("delete/{id}")
    fun deleteExercise(@PathVariable("id") exerciseId: String): String {
        exerciseRepository.deleteById(exerciseId)
        return "redirect:/exercise"
    }

    @GetMapping("edit/{id}")
    fun showEditExercisePage(@PathVariable("id") exerciseId: String, model: Model): String {
        logger.info("Edit exercise $exerciseId")
        val exercise = exerciseRepository.findById(exerciseId).orElseThrow()

        var exerciseForm = ExerciseForm(
            name = exercise.name,
            new = false,
            id = exerciseId
        )
        model.addAttribute("exercise", exerciseForm)
        return "exercise-view"
    }
}