package br.com.forum.dto

import br.com.forum.model.Usuario
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class NovoTopicoForm (
    val id: Long?,
    @field:NotEmpty @field:Size(min = 5, max = 100)
    val titulo: String,
    @field:NotEmpty @field:Size(min = 10, max = 1000)
    val mensagem: String,
    @field:NotNull
    val idCurso: Long,
    @field:NotNull
    val idUsuario: Long
)
