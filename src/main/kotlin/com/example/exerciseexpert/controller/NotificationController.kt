package com.example.exerciseexpert.controller

import com.example.exerciseexpert.domain.Notification
import com.example.exerciseexpert.repository.MessageRepository
import com.example.exerciseexpert.repository.NotificationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

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

    @PostMapping("{notificationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun readNotification(@PathVariable notificationId: String) {
        val notification = notificationRepository.findById(notificationId).orElseThrow {
            Exception("Could not find notification by id $notificationId")
        }

        if (user().id!! != notification.userId) {
            throw Exception("Only notifications for current use could be marked as read")
        }

        notification.viewed = true
        notificationRepository.save(notification)
    }

    fun getNotifications(): List<Notification> {
        return notificationRepository.findByUserIdAndViewed(user().id!!, false)
    }
}