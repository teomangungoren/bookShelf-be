package com.microLib.library.config

import com.microLib.library.service.LogoutService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter
import org.springframework.security.web.util.matcher.RequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val authenticationProvider: AuthenticationProvider,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val logoutService: LogoutService
) {


    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

       // val clearSideData:HeaderWriterLogoutHandler= HeaderWriterLogoutHandler(ClearSiteDataHeaderWriter())

        return http
            .cors { it.disable() }
            .csrf { it.disable() }
            .authorizeHttpRequests {
                    requests ->
                requests
                    .requestMatchers(RequestMatcher { request ->
                        request.servletPath == "/api/v1/users/**"})
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            }
            .sessionManagement {
                    sessionManagement ->
                sessionManagement
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .logout {logout->
                logout.logoutUrl("/api/v1/users/logout")
                logout.addLogoutHandler(logoutService)
                    .logoutSuccessHandler { request, response, authentication -> SecurityContextHolder.clearContext() }
            }
            .build()
    }
}
