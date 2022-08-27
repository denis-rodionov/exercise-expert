package com.example.exerciseexpert.repository

import com.example.exerciseexpert.domain.Notification
import org.springframework.data.repository.CrudRepository

interface NotificationRepository: CrudRepository<Notification, String> {
}