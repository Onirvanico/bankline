package br.com.projeto.banklinesantander.data

import br.com.projeto.banklinesantander.domain.Movimentacao
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface MovimentacaoService {

    @GET("movimentacoes/{id}")
    suspend fun retrieveMovimentacoes(@Path("id") id: Int): List<Movimentacao>

}