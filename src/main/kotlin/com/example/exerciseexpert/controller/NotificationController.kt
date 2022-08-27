package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.Notification
import com.example.exerciseexpert.repository.MessageRepository
import com.example.exerciseexpert.repository.NotificationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/notification")
class NotificationController: BaseController() {
    @Autowired
    lateinit var notificationRepository: NotificationRepository

    @GetMapping("/count")
    @ResponseBody
    fun getNotificationsForCurrentUser(): String {
        val notifications = getNotifications()
        return notifications.size.toString()
    }

    @GetMapping
    fun getUserNotifications(model: Model): String {
        val notifications = getNotifications()

        model.addAttribute("notifications", notifications)

        return "notifications"
    }

    fun getNotifications(): List<Notification> {
        return notificationRepository.findByUserIdAndViewed(user().id!!, false)
    }
}