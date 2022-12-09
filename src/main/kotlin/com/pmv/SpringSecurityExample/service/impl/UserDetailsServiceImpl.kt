package com.pmv.SpringSecurityExample.service.impl

import com.pmv.SpringSecurityExample.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UserDetailsServiceImpl: UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username).get()

        val authorities: List<GrantedAuthority> = user.roles!!.stream().map { role -> SimpleGrantedAuthority(role.name) }
            .collect(
            Collectors.toList<GrantedAuthority>())

        return org.springframework.security.core.userdetails.User
            .withUsername(username)
            .password(user.password)
            .authorities(authorities)
            .accountExpired(user.expired)
            .accountLocked(user.locked!!)
            .credentialsExpired(false)
            .disabled(false)
            .build()
    }
}