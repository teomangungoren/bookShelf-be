package com.microLib.library.service

import com.microLib.library.domain.dto.UserRegisterRequest
import com.microLib.library.domain.dto.UserResponse
import com.microLib.library.exception.UserAlreadyExistException
import com.microLib.library.exception.UserNotFoundException
import com.microLib.library.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    @Transactional
    fun registerUser(userRegisterRequest: UserRegisterRequest) {
        if (existByEmail(userRegisterRequest.email)) {
            throw UserAlreadyExistException("User with email ${userRegisterRequest.email} already exist")
        }
        val user = UserRegisterRequest.toUser(userRegisterRequest)
        userRepository.save(user)
    }

    @Transactional
    fun existByEmail(email: String): Boolean {
        return userRepository.existByEmail(email)
    }

    fun getById(id: String): UserResponse? {
        val user = userRepository.findUserById(id)
        return user?.let { UserResponse.convert(it) } ?: throw UserNotFoundException("User with id $id not found")
    }
}
