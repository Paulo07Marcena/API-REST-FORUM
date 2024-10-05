package br.com.forum.service

import br.com.forum.model.Curso
import br.com.forum.repository.CursoRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(
    private val repository: CursoRepository
) {

    fun buscarPorId(id: Long): Curso {
        return repository.getOne(id)
    }
}
