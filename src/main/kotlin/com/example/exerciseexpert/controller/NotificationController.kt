package com.example.exerciseexpert.controller

import com.example.exerciseexpert.repository.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/notification")
class NotificationController: BaseController() {
    @Autowired
    lateinit var messageRepository: MessageRepository

    @GetMapping
    @ResponseBody
    fun getNotificationsForCurrentUser(): String {
        return "43"
    }
}