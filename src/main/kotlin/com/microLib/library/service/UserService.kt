package com.microLib.library.service

import com.microLib.library.domain.enum.TokenType
import com.microLib.library.domain.model.Token
import com.microLib.library.domain.model.User
import com.microLib.library.domain.request.ChangePasswordRequest
import com.microLib.library.domain.request.RegisterUserRequest
import com.microLib.library.domain.request.SignInRequest
import com.microLib.library.domain.response.AuthenticationResponse
import com.microLib.library.domain.response.UserResponse
import com.microLib.library.exception.UserAlreadyExistException
import com.microLib.library.exception.UserNotFoundException
import com.microLib.library.repository.TokenRepository
import com.microLib.library.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class UserService(private val userRepository: UserRepository,
                  private val jwtService: JwtService,
                  private val tokenRepository: TokenRepository,
                  private val authenticationManager: AuthenticationManager,
                  private val passwordEncoder: PasswordEncoder) {


    fun registerUser(request: RegisterUserRequest): AuthenticationResponse {
        if (userRepository.existsByEmail(request.email) || userRepository.existsByPhoneNumber(request.phoneNumber)) {
                throw UserAlreadyExistException("User with email ${request.email} already exist")
        }
        val user=userRepository.save(RegisterUserRequest.toUser(request,passwordEncoder ))
       val jwtToken=jwtService.generateToken(user)
        createToken(jwtToken, user)
        return AuthenticationResponse(jwtToken)
    }

    fun changePassword(request: ChangePasswordRequest, principal: Principal){
        val user=(principal as UsernamePasswordAuthenticationToken).principal as User

        if(!passwordEncoder.matches(request.oldPassword,user.password)){
            throw IllegalArgumentException("Old password is not correct")
        }
        if(!request.newPassword.equals(request.confirmPassword)){
            throw IllegalArgumentException("New password and confirm password does not match")
        }
        user.password=passwordEncoder.encode(request.newPassword)
        userRepository.save(user)
    }

    fun authenticate(request: SignInRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password
            )
        )
        val user= userRepository.findByEmail(request.email)?:
        throw UserNotFoundException("User with email ${request.email} not found")
       val jwtToken= jwtService.generateToken(user)
        revokeAllUserTokens(user)
        createToken(jwtToken, user)
        return AuthenticationResponse(jwtToken)
    }

    private fun createToken(jwtToken: String, user: User) {
        val token = Token(
            null,
            jwtToken,
            TokenType.BEARER,
            false,
            false,
            user
        )
        tokenRepository.save(token)
    }

    private fun revokeAllUserTokens(user:User){
        val validToken=tokenRepository.findAllValidTokensByUser(user.id!!)
        if(validToken.isEmpty()){
            return
        }
        validToken.forEach{
            it.revoked=true
            it.expired=true
        }
        tokenRepository.saveAll(validToken)
    }
    fun getById(id: String): UserResponse? {
        val user = userRepository.findUserById(id)
        return user?.let { UserResponse.convert(it) } ?: throw UserNotFoundException("User with id $id not found")
    }

}
