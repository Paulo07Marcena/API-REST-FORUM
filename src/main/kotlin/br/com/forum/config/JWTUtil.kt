package br.com.forum.config

import br.com.forum.service.UsuarioService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import br.com.forum.config.Constants.Companion.pathJWTSecret
import br.com.forum.config.Constants.Companion.tokenExperiedTime
import br.com.forum.model.Role
import java.util.*

@Component
class JWTUtil(
    private val usuarioService: UsuarioService
) {

    @Value(pathJWTSecret)
    private lateinit var secret: String

    fun generateToken(username: String, authorities: List<Role>): String {
        return Jwts.builder()
            .setSubject(username)
            .claim("role", authorities)
            .setExpiration(Date(System.currentTimeMillis() + tokenExperiedTime))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .compact()
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwt.toString())
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun getAuthentication(jwt: String?): Authentication {
        val username = Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwt).body.subject
        val roles = usuarioService.loadUserByUsername(username).authorities
        return UsernamePasswordAuthenticationToken(username, null, roles)
    }
}