package br.com.forum.controller

import br.com.forum.model.Curso
import br.com.forum.model.Topico
import br.com.forum.model.Usuario
import br.com.forum.service.TopicoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topicos")
class TopicoController(
    private val service: TopicoService
) {

    @GetMapping
    fun listar(): List<Topico> {
        return service.listar();
    }
}