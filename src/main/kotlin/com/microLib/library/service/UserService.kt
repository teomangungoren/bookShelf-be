package com.microLib.library.service

import com.microLib.library.domain.dto.AuthenticationResponse
import com.microLib.library.domain.dto.RegisterUserRequest
import com.microLib.library.domain.dto.SignInRequest
import com.microLib.library.domain.dto.UserResponse
import com.microLib.library.exception.UserAlreadyExistException
import com.microLib.library.exception.UserNotFoundException
import com.microLib.library.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository,
                  private val jwtService: JwtService,
                  private val authenticationManager: AuthenticationManager) {

    @Transactional
    fun registerUser(registerUserRequest: RegisterUserRequest):AuthenticationResponse{
        if (existByEmail(registerUserRequest.email)) {
            throw UserAlreadyExistException("User with email ${registerUserRequest.email} already exist")
        }
        val user = RegisterUserRequest.toUser(registerUserRequest)
        userRepository.save(user)
       val jwtToken=jwtService.generateToken(user)
        return AuthenticationResponse(jwtToken)

    }

    fun authenticate(signInRequest: SignInRequest):AuthenticationResponse{
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                signInRequest.email,
                signInRequest.password
            )
        )
        val user= userRepository.findByEmail(signInRequest.email)
        return user?.let { AuthenticationResponse(jwtService.generateToken(it)) }
            ?: throw UserNotFoundException("User with email ${signInRequest.email} not found")

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
