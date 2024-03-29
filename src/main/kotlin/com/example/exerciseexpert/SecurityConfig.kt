package com.example.exerciseexpert

import com.example.exerciseexpert.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var authService: AuthService

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(authService).passwordEncoder(encoder());
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/user/**").hasAuthority("ADMIN")
            .antMatchers("/exercise/**").hasAuthority("TEACHER")
            .antMatchers("/auth/register", "/login", "/css/**", "/js/**").permitAll()
            .anyRequest().authenticated()
            .and().formLogin().loginPage("/login")
            .loginProcessingUrl("/login")
            .usernameParameter("email")
            .passwordParameter("password")
            .defaultSuccessUrl("/", true)
            .and()
            .csrf().disable().cors()
    }

    @Bean
    fun encoder(): PasswordEncoder? {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }
}