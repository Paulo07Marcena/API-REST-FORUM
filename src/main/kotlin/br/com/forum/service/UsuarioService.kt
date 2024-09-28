package br.com.forum.service

import br.com.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(
    var usuarios: List<Usuario>
) {

    init {
        val usuario = Usuario(
            id = 1,
            nome = "Rafael",
            email = "raphael@gmail.com"
        )

        usuarios = Arrays.asList(usuario)
    }

    fun buscarPorId(id: Long): Usuario {
        return usuarios
            .stream()
            .filter(
                { u -> u.id == id }
            ).findFirst().get()
    }

}
