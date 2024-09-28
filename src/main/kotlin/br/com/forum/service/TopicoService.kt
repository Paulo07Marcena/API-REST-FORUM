package br.com.forum.service

import br.com.forum.dto.NovoTopicoForm
import br.com.forum.dto.TopicoView
import br.com.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private var cursoService: CursoService,
    private var usuarioService: UsuarioService
) {

    fun listar(): List<TopicoView> {
        return topicos.stream().map{ t -> TopicoView(
            id = t.id,
            titulo = t.titulo,
            mensagem = t.mensagem,
            status = t.status,
            dataCriacao = t.dataCriacao
        ) }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        var topico = topicos
            .stream()
            .filter(
                { t -> t.id == id }
            ).findFirst().get()

        return TopicoView(
            id = topico.id,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        )
    }

    fun cadastrar(dto: NovoTopicoForm) {
        topicos = topicos.plus(
            Topico(
                id = topicos.size.toLong() + 1,
                titulo = dto.titulo,
                mensagem = dto.mensagem,
                curso = cursoService.buscarPorId(dto.idCurso),
                autor = usuarioService.buscarPorId(dto.idAutor)
            )
        )
    }
}