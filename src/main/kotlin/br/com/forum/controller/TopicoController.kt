package br.com.forum.controller

import br.com.forum.model.Curso
import br.com.forum.model.Topico
import br.com.forum.model.Usuario
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topicos")
class TopicoController {

    @GetMapping
    fun listar(): List<Topico> {
        val topicos = ArrayList<Topico>()
        topicos.add(
            Topico(
                id = 1,
                titulo = "Duvida",
                mensagem = "Duvida com Spring",
                curso = Curso(1,"Kotlin", "Programação"),
                autor = Usuario(1, "Rafael", "rafael@gmail.com")
            ))
        topicos.add(
            Topico(
                id = 1,
                titulo = "Duvida",
                mensagem = "Duvida com Spring",
                curso = Curso(1,"Kotlin", "Programação"),
                autor = Usuario(1, "Rafael", "rafael@gmail.com")
            ))
        return topicos
    }
}