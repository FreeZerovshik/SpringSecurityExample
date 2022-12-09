package com.pmv.rest.parser.jwt

import com.pmv.rest.parser.model.users.User
import org.springframework.security.core.GrantedAuthority

class JwtResponse(var accessToken: String?, var user: User?, val authorities: Collection<GrantedAuthority>) {
    var type = "Bearer"
}