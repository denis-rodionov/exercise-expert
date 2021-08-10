package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.Exercise
import com.example.exerciseexpert.domain.UserContext
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
@Validated
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
        logger.info("User ${userContext.user} created exercise")
        newExercise.author = userContext.user.toString()

        exerciseRepository.save(newExercise)
        return "redirect:/exercise"
    }
}