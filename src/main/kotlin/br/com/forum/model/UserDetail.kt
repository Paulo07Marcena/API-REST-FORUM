package br.com.forum.model

import org.springframework.security.core.userdetails.UserDetails

class UserDetail(
    private val usuario: Usuario
): UserDetails {

    override fun getAuthorities() = usuario.roles

    override fun getPassword() = usuario.password

    override fun getUsername() = usuario.email

}