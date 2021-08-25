package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.Exercise
import com.example.exerciseexpert.domain.UserContext
import com.example.exerciseexpert.domain.UserName
import com.example.exerciseexpert.form.CreateExercise
import com.example.exerciseexpert.repository.ExerciseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
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
        model.addAttribute("newExercise", CreateExercise())
        return "create-exercise"
    }

    @PostMapping
    fun createExercise(@ModelAttribute(name = "newExercise") @Valid newExercise: CreateExercise,
                       errors: Errors,
                       model: Model,
                       @ModelAttribute userContext: UserContext,): String {
        logger.info("DEBUG: creating exercise...")
        if (errors.hasErrors()) {
            return "create-exercise"
        }
        logger.info("User ${userContext.user} created exercise")

        userContext.user?.let { author ->
            val exercise = Exercise(null, newExercise.name!!,
                UserName(null, author.name, author.id!!), author.name)
            exerciseRepository.save(exercise)
        } ?: throw Exception("Current user is not known")

        return "redirect:/exercise"
    }

    @GetMapping("delete/{id}")
    fun deleteExercise(@PathVariable("id") exerciseId: String, model: Model): String? {
        exerciseRepository.deleteById(exerciseId);
        return "redirect:/exercise"
    }
}