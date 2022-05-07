package br.com.projeto.banklinesantander.domain

data class Movimentacao(
    val id: Int,
    val dataHora: String,
    val descricao: String,
    val valor: Double,
    val tipoMovimentacao: TipoMovimentacao,
    val idConta: Int
)
