package com.microLib.library.service

import com.fasterxml.jackson.databind.ObjectMapper
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
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository,
                  private val jwtService: JwtService,
                  private val tokenRepository: TokenRepository,
                  private val authenticationManager: AuthenticationManager,
                  private val passwordEncoder: PasswordEncoder) {


    fun registerUser(request: RegisterUserRequest): AuthenticationResponse {
        if (userRepository.existsByEmail(request.email) || userRepository.existsByPhoneNumber(request.phoneNumber)) {
                throw UserAlreadyExistException("User already exist")
        }
        val user=userRepository.save(RegisterUserRequest.toUser(request,passwordEncoder ))
       val jwtToken=jwtService.generateToken(user)
        val refreshToken=jwtService.generateRefreshToken(user)
        createToken(jwtToken, user)
        return AuthenticationResponse(jwtToken,refreshToken)
    }

    fun changePassword(request: ChangePasswordRequest){
        val user=userRepository.findByEmail(SecurityContextHolder.getContext().authentication.name)?:throw UserNotFoundException("User not found")

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
        val refreshToken=jwtService.generateRefreshToken(user)
        revokeAllUserTokens(user)
        createToken(jwtToken, user)
        return AuthenticationResponse(jwtToken,refreshToken)
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

    fun refreshToken(request:HttpServletRequest,response:HttpServletResponse){
        val authHeader=request.getHeader(HttpHeaders.AUTHORIZATION)
        if(authHeader==null || !authHeader.startsWith("Bearer ")) return
        val refreshToken=authHeader.substring(7)
        val userEmail=jwtService.extractUsername(refreshToken)
        if(userEmail!=null){
            val user=userRepository.findByEmail(userEmail)?:throw UserNotFoundException("User with email $userEmail not found")
            if(jwtService.isValidToken(refreshToken,user)){
                val accessToken=jwtService.generateToken(user)
                revokeAllUserTokens(user)
                createToken(accessToken,user)
                val authenticationResponse=AuthenticationResponse(accessToken,refreshToken)
                ObjectMapper().writeValue(response.outputStream,authenticationResponse)

            }
        }


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
