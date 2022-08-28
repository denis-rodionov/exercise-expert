package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.Message
import com.example.exerciseexpert.repository.MessageRepository
import com.example.exerciseexpert.service.NotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.time.Instant

@Controller
@RequestMapping("/message")
class MessageController: BaseController() {
    @Autowired
    lateinit var messageRepository: MessageRepository

    @Autowired
    lateinit var notificationService: NotificationService

    @PostMapping
    fun sendComment(@RequestParam returnUrl: String, @RequestParam message: String,
                    @RequestParam assignedExerciseId: String): String {
        logger.info("ReturnUrl: $returnUrl, message: $message, assignedExerciseId: $assignedExerciseId")
        val user = user()

        val savedMessage: Message = messageRepository.save(Message(
            authorId = user().id!!,
            authorName = user.name,
            recipientId = null,
            assignedExerciseId = assignedExerciseId,
            message = message,
            timestamp = Instant.now(),
        ))
        logger.info("Message is saved with id ${savedMessage.id}")
        notificationService.exerciseCommented(assignedExerciseId, user, savedMessage.id!!)

        return "redirect:$returnUrl"
    }

    @DeleteMapping("{messageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteMessage(@PathVariable messageId: String) {
        logger.info("Deleting message $messageId")
        messageRepository.deleteById(messageId)
    }
}