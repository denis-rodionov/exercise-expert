package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.Exercise
import com.example.exerciseexpert.domain.User
import com.example.exerciseexpert.domain.UserContext
import com.example.exerciseexpert.repository.ExerciseRepository
import org.slf4j.LoggerFactory
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
        if (userContext.user == null) {
            return "redirect:/auth"
        }
        model.addAttribute("exercises", exerciseRepository.getExercises())
        return "exercise-list"
    }

    @GetMapping("/create")
    fun createExerciseForm(model: Model): String {
        model.addAttribute("newExercise", Exercise())
        return "create-exercise"
    }

    @PostMapping
    fun createExercise(@ModelAttribute(value = "newExercise") @Valid newExercise: Exercise,
                       @ModelAttribute userContext: UserContext,
                       errors: Errors, model: Model): String {
        if (errors.hasErrors()) {
            return "create-exercise"
        }
        if (userContext.user == null) {
            return "redirect:/auth"
        }
        logger.info("User ${userContext.user} created exercise")
        newExercise.author = userContext.user.toString()

        exerciseRepository.createExercise(newExercise)
        return "redirect:/exercise"
    }
}