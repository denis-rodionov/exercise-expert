package com.example.exerciseexpert.controller

import com.example.exerciseexpert.form.UserEditForm
import com.example.exerciseexpert.repository.UserRepository
import com.example.exerciseexpert.service.UserService
import org.bson.BsonBinarySubType
import org.bson.types.Binary
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Controller
@RequestMapping("/profile")
class ProfileController: BaseController() {
    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var userRepository: UserRepository

    @GetMapping
    fun getProfileView(model: Model): String {
        val contextUser = userContext().user ?: throw Exception("No logged user!")
        val user = userRepository.findById(contextUser.id!!).orElseThrow { throw Exception("No such user in DB") }
        val userForm = UserEditForm(user.id!!, user.name, user.email, "", user.role, user.supervisorUserId,
            user.avatarBase64)

        model.addAttribute("user", userForm)
        model.addAttribute("supervisors", userService.getSupervisors())
        return "profile"
    }

    @PostMapping
    fun saveProfile(@ModelAttribute userForm: UserEditForm, @RequestParam("avatar") avatarFile: MultipartFile): String {
        val userId = userContext().user?.id ?: throw Exception("No logged user")
        val domainUser = userRepository.findById(userId).orElseThrow { throw Exception("Cannot find user in the database") }

        if (userForm.name.isNotBlank()) {
            domainUser.name = userForm.name
        }

        userForm.supervisorUserId?.let {
            if (it.isNotBlank()) {
                domainUser.supervisorUserId = it
            }
        }

        userForm.password?.let {
            if (it.isNotBlank()) {
                domainUser.userPassword = it
            }
        }

        if (!avatarFile.isEmpty) {
            val binaryAvatar = Binary(BsonBinarySubType.BINARY, avatarFile.bytes)
            domainUser.avatarBase64 = Base64.getEncoder().encodeToString(binaryAvatar.data)
        }

        userRepository.save(domainUser)
        return "redirect:/profile"
    }
}