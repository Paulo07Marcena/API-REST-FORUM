package br.com.forum.service

import br.com.forum.model.UserDetail
import br.com.forum.model.Usuario
import br.com.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val repository: UsuarioRepository
): UserDetailsService {

    fun buscarPorId(id: Long): Usuario {
        return repository.getOne(id)
    }

    override fun loadUserByUsername(email: String?): UserDetails {
        val usuario = repository.findByEmail(email) ?: throw RuntimeException("Usuário não encontrado")
        return UserDetail(usuario)
    }

}
