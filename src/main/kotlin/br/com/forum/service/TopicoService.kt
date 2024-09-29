package br.com.forum.service

import br.com.forum.dto.AtualizacaoTopicoForm
import br.com.forum.dto.NovoTopicoForm
import br.com.forum.dto.TopicoView
import br.com.forum.exception.NotFoundException
import br.com.forum.mapper.TopicoFormMapper
import br.com.forum.mapper.TopicoViewMapper
import br.com.forum.model.Topico
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.net.URI
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Tópico não encontrado"
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
            ) .findFirst().orElseThrow{
                NotFoundException(notFoundMessage)
            }

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(dto: NovoTopicoForm): TopicoView {
        var topico = topicoFormMapper.map(dto)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(dto: AtualizacaoTopicoForm): TopicoView {
        var topico = topicos.stream().filter(
                { t -> t.id == dto.id }
            ).findFirst().orElseThrow{
                NotFoundException(notFoundMessage)
        }

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

        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        var topico = topicos.stream().filter(
                { t -> t.id == id }
            ).findFirst().orElseThrow{
                NotFoundException(notFoundMessage)
            }

        topicos = topicos.minus(topico)
    }
}