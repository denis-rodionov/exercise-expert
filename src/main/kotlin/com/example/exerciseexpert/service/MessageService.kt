package com.example.exerciseexpert.service

import com.example.exerciseexpert.domain.Message
import com.example.exerciseexpert.repository.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MessageService {
    @Autowired
    lateinit var messageRepository: MessageRepository

    // get all direct messages, written and received by the user
    fun getMyDirectMessages(userId: String): List<Message> {
        var messagesTo = messageRepository.findByAuthorIdAndAssignedExerciseIdOrderByTimestampAsc(userId, null)
        var messagesFrom = messageRepository.findByRecipientIdAndAssignedExerciseIdOrderByTimestampAsc(userId, null)
        return combineMessages(messagesTo, messagesFrom)
    }

    fun getDirectMessagesBetweenTwoUsers(user1Id: String, user2Id: String): List<Message> {
        val messagesTo =
            messageRepository.findByRecipientIdAndAuthorIdOrderByTimestampAsc(user1Id, user2Id)
        val messagesFrom =
            messageRepository.findByRecipientIdAndAuthorIdOrderByTimestampAsc(user2Id, user1Id)
        return combineMessages(messagesTo, messagesFrom)
    }

    private fun combineMessages(list1: List<Message>, list2: List<Message>) : List<Message> {
        return (list1 + list2).sortedByDescending { it.timestamp }
    }
}