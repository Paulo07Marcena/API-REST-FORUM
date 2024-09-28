package br.com.forum.service

import br.com.forum.model.Curso
import br.com.forum.model.Topico
import br.com.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var topicos: List<Topico>
) {

    init {
        var topico1 =
            Topico(
                id = 1,
                titulo = "Duvida",
                mensagem = "Duvida com Spring",
                curso = Curso(1,"Kotlin", "Programação"),
                autor = Usuario(1, "Rafael", "rafael@gmail.com")
            )

        var topico2 =
            Topico(
                id = 2,
                titulo = "Duvida",
                mensagem = "Duvida com Kotlin",
                curso = Curso(1,"Kotlin", "Programação"),
                autor = Usuario(1, "Rafael", "rafael@gmail.com")
            )

        topicos = arrayListOf(topico1, topico2)
    }

    fun listar(): List<Topico> {
        return topicos
    }

    fun buscarPorId(id: Long): Topico {
        return topicos
            .stream()
            .filter(
                { t -> t.id == id }
            ).findFirst().get()
    }
}