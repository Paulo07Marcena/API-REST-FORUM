package br.com.forum.service

import br.com.forum.dto.AtualizacaoTopicoForm
import br.com.forum.dto.NovoTopicoForm
import br.com.forum.dto.TopicoView
import br.com.forum.exception.NotFoundException
import br.com.forum.mapper.TopicoFormMapper
import br.com.forum.mapper.TopicoViewMapper
import br.com.forum.model.Topico
import br.com.forum.repository.TopicoRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.net.URI
import java.util.stream.Collectors

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Tópico não encontrado"
) {

    fun listar(nomeCurso: String?): List<TopicoView> {

        val topicos = if (nomeCurso == null) {
            repository.findAll()
        } else {
            repository.findByCursoNome(nomeCurso)
        }

        return topicos.stream().map(
                { t -> topicoViewMapper.map(t) }
            ).collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        var topico = repository.findById(id).orElseThrow{
            NotFoundException(notFoundMessage)
        }
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(dto: NovoTopicoForm): TopicoView {
        var topico = topicoFormMapper.map(dto)
        repository.save(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(dto: AtualizacaoTopicoForm): TopicoView {
        var topico = repository.findById(dto.id).orElseThrow{
                NotFoundException(notFoundMessage)
        }

        topico.titulo = dto.titulo
        topico.mensagem = dto.mensagem


        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }
}