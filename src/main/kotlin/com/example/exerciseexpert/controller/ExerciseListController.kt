package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.Exercise
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/exercise")
class ExerciseListController {

    val exerciseList: MutableList<Exercise> = mutableListOf(
        Exercise(id = 1, name = "Exercise 1"),
        Exercise(id = 2, name = "Exercise 2")
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
    fun createExercise(exercise: Exercise, model: Model): String {
        exerciseList.add(exercise)
        return "redirect:/exercise"
    }
}