package br.com.forum.repository

import br.com.forum.dto.TopicoPorCategoriaDto
import br.com.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository: JpaRepository<Topico, Long>  {

    fun findByCursoNome(nomeCurso: String, paginacao: Pageable): Page<Topico>

    @Query(
        "SELECT " +
            "new br.com.forum.dto.TopicoPorCategoriaDto(curso.categoria, count(t)) as quantidade " +
            "FROM Topico t " +
            "JOIN t.curso curso " +
            "GROUP BY curso.categoria "
    )
    fun relatorio(): List<TopicoPorCategoriaDto>

    @Query("SELECT t FROM Topico t WHERE t.respostas IS EMPTY")
    fun topicosSemResposta(): List<Topico>
}