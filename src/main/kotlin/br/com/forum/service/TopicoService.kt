package br.com.forum.service

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
}