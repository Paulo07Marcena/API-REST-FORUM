package br.com.forum.model

data class Resposta (
    val id: Long? = null,
    val mensagem: String,
    val topico: Topico,
    val autor: Usuario,
    val solucao: Boolean
)
