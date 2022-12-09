package com.pmv.SpringSecurityExample.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*


@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = 0,

    @Column(name="username")
    var username: String,

    @Column(name="first_name")
    var firstName: String,

    @Column(name="last_name")
    var lastName: String,

    @Column(name="email")
    var email: String,

    @Column(name="password")
    @JsonIgnore
    var password: String,

    @Column(name="locked")
    var locked: Boolean = false,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: Collection<Role>? = null,

    @Column(name="expired")
    var expired: Boolean = true,

)
