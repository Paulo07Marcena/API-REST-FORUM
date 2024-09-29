package br.com.forum.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class AtualizacaoTopicoForm (
    @field:NotNull
    val id: Long,
    @field:NotNull @field:Size(min = 5, max = 100)
    val titulo: String,
    @field:NotNull @field:Size(min = 5, max = 100)
    val mensagem: String
)
