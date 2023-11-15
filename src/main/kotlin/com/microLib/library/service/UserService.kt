package com.microLib.library.service

import com.microLib.library.domain.enum.TokenType
import com.microLib.library.domain.model.Token
import com.microLib.library.domain.model.User
import com.microLib.library.domain.request.ChangePasswordRequest
import com.microLib.library.domain.request.RegisterUserRequest
import com.microLib.library.domain.request.SignInRequest
import com.microLib.library.domain.response.TokenResponse
import com.microLib.library.domain.response.UserResponse
import com.microLib.library.exception.UserAlreadyExistException
import com.microLib.library.exception.UserNotFoundException
import com.microLib.library.repository.TokenRepository
import com.microLib.library.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.Date

@Service
class UserService(private val userRepository: UserRepository,
                  private val tokenService: TokenService,
                  private val tokenRepository: TokenRepository,
                  private val authenticationManager: AuthenticationManager,
                  private val passwordEncoder: PasswordEncoder) {


    fun registerUser(request: RegisterUserRequest): UserResponse {
        if (userRepository.existsByEmail(request.email) || userRepository.existsByPhoneNumber(request.phoneNumber)) {
                throw UserAlreadyExistException("User already exist")
        }
        val user=userRepository.save(RegisterUserRequest.toUser(request,passwordEncoder ))
        return UserResponse.convert(user)
    }

    fun changePassword(request: ChangePasswordRequest){
        val user=userRepository.findByEmail(SecurityContextHolder.getContext().authentication.name)?:throw UserNotFoundException("User not found")
           require(passwordEncoder.matches(request.oldPassword,user.password)){ throw IllegalArgumentException("Old password is not correct") }
           require(request.newPassword.equals(request.confirmPassword)){ throw IllegalArgumentException("New password and confirm password does not match") }
           user.password=passwordEncoder.encode(request.newPassword)
           userRepository.save(user)
    }

    fun authenticate(request: SignInRequest): TokenResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password
            )
        )
        val user= userRepository.findByEmail(request.email)?:
        throw UserNotFoundException("User with email ${request.email} not found")
       val jwtToken= tokenService.generateToken(user)
        val expirationDate=tokenService.extractExpiration(jwtToken)
        revokeAllUserTokens(user)
        tokenRepository.save(Token("",jwtToken,TokenType.BEARER,false,false,user))
        return TokenResponse(jwtToken,Date(System.currentTimeMillis()),expirationDate)
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
    fun getById(id: String): User {
        return userRepository.findUserById(id)?:throw UserNotFoundException("User with id $id not found")
    }

}
