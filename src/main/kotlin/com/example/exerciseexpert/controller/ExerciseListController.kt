package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.Exercise
import com.example.exerciseexpert.domain.UserContext
import com.example.exerciseexpert.form.CreateExercise
import com.example.exerciseexpert.repository.ExerciseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid


@Controller
@RequestMapping("/exercise")
class ExerciseListController : BaseController() {
    @Autowired
    lateinit var exerciseRepository: ExerciseRepository

    @GetMapping
    fun getAllExercises(@ModelAttribute userContext: UserContext, model: Model): String {
        model.addAttribute("exercises", exerciseRepository.findAll())
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
            logger.info("DEBUG: errors found")
            return "create-exercise"
        }
        logger.info("User ${userContext.user} created exercise")

        val author = userContext.user.toString()
        val exercise = Exercise(null, newExercise.name!!, author)

        exerciseRepository.save(exercise)
        return "redirect:/exercise"
    }
}