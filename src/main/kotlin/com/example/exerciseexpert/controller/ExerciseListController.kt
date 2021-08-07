package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.Exercise
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

    val exerciseList: MutableList<Exercise> = mutableListOf(
        Exercise(name = "Exercise 1"),
        Exercise(name = "Exercise 2")
    )

    @GetMapping
    fun getAllExercises(model: Model): String {
        model.addAttribute("exercises", exerciseList)
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
        exerciseList.add(newExercise)
        return "redirect:/exercise"
    }
}