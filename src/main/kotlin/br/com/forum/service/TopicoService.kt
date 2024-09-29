package br.com.forum.service

import br.com.forum.dto.AtualizacaoTopicoForm
import br.com.forum.dto.NovoTopicoForm
import br.com.forum.dto.TopicoView
import br.com.forum.mapper.TopicoFormMapper
import br.com.forum.mapper.TopicoViewMapper
import br.com.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper
) {

    fun listar(): List<TopicoView> {
        return  topicos.stream().map(
                { t -> topicoViewMapper.map(t) }
            ).collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        var topico = topicos
            .stream()
            .filter(
                { t -> t.id == id }
            ) .findFirst().get()

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(dto: NovoTopicoForm) {
        var topico = topicoFormMapper.map(dto)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)
    }

    fun atualizar(dto: AtualizacaoTopicoForm) {
        var topico = topicos.stream().filter(
                { t -> t.id == dto.id }
            ).findFirst().get()

        topicos = topicos.minus(topico).plus(
            Topico(
                id = dto.id,
                titulo = dto.titulo,
                curso = topico.curso,
                autor = topico.autor,
                mensagem = dto.mensagem,
                dataCriacao = topico.dataCriacao,
                status = topico.status,
                respostas = topico.respostas
            )
        )
    }

    fun deletar(id: Long) {
        var topico = topicos.stream().filter(
                { t -> t.id == id }
            ).findFirst().get()

        topicos = topicos.minus(topico)
    }
}