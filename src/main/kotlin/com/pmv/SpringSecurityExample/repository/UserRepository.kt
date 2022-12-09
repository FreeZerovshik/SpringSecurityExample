package com.pmv.SpringSecurityExample.repository;

import com.pmv.SpringSecurityExample.model.Role
import com.pmv.SpringSecurityExample.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(@Param("username") username: String): Optional<User>

    fun findByEmail(@Param("email") email: String): Optional<User>

    fun findByRoles(@Param("role") role: Role): MutableList<User>
}