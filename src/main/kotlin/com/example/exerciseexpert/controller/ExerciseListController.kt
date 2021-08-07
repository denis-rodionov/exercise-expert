package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.Exercise
import com.example.exerciseexpert.repository.ExerciseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/exercise")
class ExerciseListController {

    @Autowired
    lateinit var exerciseRepository: ExerciseRepository

    @GetMapping
    fun getAllExercises(model: Model): String {
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
                       errors: Errors, model: Model): String {
        if (errors.hasErrors()) {
            return "create-exercise"
        }
        exerciseRepository.createExercise(newExercise)
        return "redirect:/exercise"
    }
}