package com.microLib.library.service

import com.microLib.library.domain.dto.AuthenticationResponse
import com.microLib.library.domain.dto.RegisterUserRequest
import com.microLib.library.domain.dto.SignInRequest
import com.microLib.library.domain.dto.UserResponse
import com.microLib.library.domain.enum.TokenType
import com.microLib.library.domain.model.Token
import com.microLib.library.domain.model.User
import com.microLib.library.exception.UserAlreadyExistException
import com.microLib.library.exception.UserNotFoundException
import com.microLib.library.repository.TokenRepository
import com.microLib.library.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository,
                  private val jwtService: JwtService,
                  private val tokenRepository: TokenRepository,
                  private val authenticationManager: AuthenticationManager) {

    @Transactional
    fun registerUser(registerUserRequest: RegisterUserRequest):AuthenticationResponse{
        if (existByEmail(registerUserRequest.email)) {
            throw UserAlreadyExistException("User with email ${registerUserRequest.email} already exist")
        }
        val user=userRepository.save(RegisterUserRequest.toUser(registerUserRequest))
       val jwtToken=jwtService.generateToken(user)
        createToken(jwtToken, user)
        return AuthenticationResponse(jwtToken)

    }



    fun authenticate(signInRequest: SignInRequest):AuthenticationResponse{
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                signInRequest.email,
                signInRequest.password
            )
        )
        val user= userRepository.findByEmail(signInRequest.email)?:
        throw UserNotFoundException("User with email ${signInRequest.email} not found")
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
        var validToken=tokenRepository.findAllValidTokensByUser(user.id!!)
        if(validToken.isEmpty()){
            return
        }
        validToken.forEach{
            it.revoked=true
            it.expired=true
        }
        tokenRepository.saveAll(validToken)
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
