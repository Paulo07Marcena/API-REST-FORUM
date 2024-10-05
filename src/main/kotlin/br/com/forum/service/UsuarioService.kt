package br.com.forum.service

import br.com.forum.model.Usuario
import br.com.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(
    private val repository: UsuarioRepository
) {

    fun buscarPorId(id: Long): Usuario {
        return repository.getOne(id)
    }

}
