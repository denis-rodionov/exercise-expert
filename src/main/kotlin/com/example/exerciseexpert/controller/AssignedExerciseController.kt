package com.example.exerciseexpert.controller

import com.example.exerciseexpert.repository.AssignedExerciseRepository
import com.example.exerciseexpert.repository.ExerciseRepository
import com.example.exerciseexpert.repository.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.time.Instant

@Controller
@RequestMapping("/assigned-exercise")
class AssignedExerciseController: BaseController() {
    @Autowired
    lateinit var assignedExerciseRepository: AssignedExerciseRepository

    @Autowired
    lateinit var exerciseRepository: ExerciseRepository

    @Autowired
    lateinit var messageRepository: MessageRepository

    @GetMapping("{assignedExerciseId}/student")
    fun showAssignedExerciseForStudent(@PathVariable assignedExerciseId: String, model: Model): String {
        val assignedExercise = assignedExerciseRepository.findById(assignedExerciseId).orElseThrow {
            throw Exception("Could not find assigned exercise with id $assignedExerciseId")
        }
        val exercise = exerciseRepository.findById(assignedExercise.exerciseId).orElseThrow {
            throw Exception("Could not find exercise with id ${assignedExercise.id}")
        }
        val comments = messageRepository.findByAssignedExerciseIdOrderByTimestampDesc(assignedExerciseId)

        model.addAttribute("assignedExercise", assignedExercise)
        model.addAttribute("exercise", exercise)
        model.addAttribute("comments", comments)

        return "exercise-student-view"
    }

    @PostMapping("{assignedExerciseId}/student")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun saveExerciseResults(@PathVariable assignedExerciseId: String,
                            @RequestParam resultText: String,
                            @RequestParam resultHtml: String,
                            @RequestParam successCount: Int,
                            @RequestParam totalCount: Int) {
        val assignedExercise = assignedExerciseRepository.findById(assignedExerciseId).orElseThrow {
            throw Exception("Could not find assigned exercise with id $assignedExerciseId")
        }

        logger.info("Score: $successCount / $totalCount")

        assignedExercise.result = resultHtml
        assignedExercise.resultShort = resultText
        assignedExercise.resultScore = successCount * 100 / totalCount
        assignedExercise.doneAt = Instant.now()

        assignedExerciseRepository.save(assignedExercise)
    }

    @GetMapping("{assignedExerciseId}/student/reset")
    fun resetExerciseResults(@PathVariable assignedExerciseId: String, model: Model): String {
        val assignedExercise = assignedExerciseRepository.findById(assignedExerciseId).orElseThrow {
            throw Exception("Could not find assigned exercise with id $assignedExerciseId")
        }
        val exercise = exerciseRepository.findById(assignedExercise.exerciseId).orElseThrow {
            throw Exception("Could not find exercise with id ${assignedExercise.id}")
        }
        val comments = messageRepository.findByAssignedExerciseIdOrderByTimestampDesc(assignedExerciseId)

        assignedExercise.result = null
        assignedExercise.resultShort = null
        assignedExercise.resultScore = null
        assignedExercise.doneAt = null

        assignedExerciseRepository.save(assignedExercise)
        model.addAttribute("assignedExercise", assignedExercise)
        model.addAttribute("exercise", exercise)
        model.addAttribute("comments", comments)

        return "exercise-student-view"
    }
}