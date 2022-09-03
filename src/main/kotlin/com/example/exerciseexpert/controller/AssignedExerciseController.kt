package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.AssignedExercise
import com.example.exerciseexpert.domain.Notification
import com.example.exerciseexpert.domain.emums.NotificationType
import com.example.exerciseexpert.domain.emums.UserRole
import com.example.exerciseexpert.repository.AssignedExerciseRepository
import com.example.exerciseexpert.repository.ExerciseRepository
import com.example.exerciseexpert.repository.MessageRepository
import com.example.exerciseexpert.repository.NotificationRepository
import com.example.exerciseexpert.service.NotificationService
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

    @Autowired
    lateinit var notificationService: NotificationService

    @GetMapping("{assignedExerciseId}")
    fun showAssignedExerciseForStudent(@PathVariable assignedExerciseId: String, model: Model): String {
        val assignedExercise = assignedExerciseRepository.findById(assignedExerciseId).orElseThrow {
            throw Exception("Could not find assigned exercise with id $assignedExerciseId")
        }
        val exercise = exerciseRepository.findById(assignedExercise.exerciseId).orElseThrow {
            throw Exception("Could not find exercise with id ${assignedExercise.id}")
        }
        val comments = messageRepository.findByAssignedExerciseIdOrderByTimestampAsc(assignedExerciseId)

        model.addAttribute("assignedExercise", assignedExercise)
        model.addAttribute("exercise", exercise)
        model.addAttribute("comments", comments)
        model.addAttribute("user", user())
        model.addAttribute("returnUrl", getReturnUrl(assignedExercise))

        return "assigned-exercise-view"
    }

    @PostMapping("{assignedExerciseId}")
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
        notificationService.exerciseCompleted(assignedExercise, user())
    }

    @GetMapping("{assignedExerciseId}/reset")
    fun resetExerciseResults(@PathVariable assignedExerciseId: String, model: Model): String {
        val assignedExercise = assignedExerciseRepository.findById(assignedExerciseId).orElseThrow {
            throw Exception("Could not find assigned exercise with id $assignedExerciseId")
        }
        val exercise = exerciseRepository.findById(assignedExercise.exerciseId).orElseThrow {
            throw Exception("Could not find exercise with id ${assignedExercise.id}")
        }
        val comments = messageRepository.findByAssignedExerciseIdOrderByTimestampAsc(assignedExerciseId)

        assignedExercise.result = null
        assignedExercise.resultShort = null
        assignedExercise.resultScore = null
        assignedExercise.doneAt = null

        assignedExerciseRepository.save(assignedExercise)
        model.addAttribute("assignedExercise", assignedExercise)
        model.addAttribute("exercise", exercise)
        model.addAttribute("comments", comments)
        model.addAttribute("user", user())
        model.addAttribute("returnUrl", getReturnUrl(assignedExercise))

        return "assigned-exercise-view"
    }

    fun getReturnUrl(assignedExercise: AssignedExercise): String {
        return when (user().role) {
            UserRole.STUDENT -> "/dashboard"
            else -> "/student/${assignedExercise.assignedToUserId}"
        }
    }
}