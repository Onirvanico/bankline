package br.com.projeto.banklinesantander.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import br.com.projeto.banklinesantander.data.MovimentacaoService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovimentacaoRepository {

    private const val BASE_URL = "https://nirvanico-bankline-api.herokuapp.com/"

    private val movimentacaoRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getMovimentacaoService() = movimentacaoRetrofit.create(MovimentacaoService::class.java)

    suspend fun getMovimentations(id: Int) = flow {
        try {
            val movimentacoes = getMovimentacaoService()
                .retrieveMovimentacoes(id)
            Log.i("MOV", movimentacoes.toString())
            emit(movimentacoes)

        } catch (ex: HttpException) {
            Log.e("RequestError", ex.message(), )
        }
    }

}