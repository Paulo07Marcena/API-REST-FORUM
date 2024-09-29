package br.com.forum.dto

data class NovoTopicoForm (
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val idCurso: Long,
    val idAutor: Long
)
