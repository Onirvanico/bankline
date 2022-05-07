package br.com.projeto.banklinesantander.presentation

import android.util.Log
import androidx.lifecycle.*
import br.com.projeto.banklinesantander.data.repository.MovimentacaoRepository
import br.com.projeto.banklinesantander.domain.Movimentacao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainPresentationViewModel: ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    fun getMovimentations(id: Int) {
        viewModelScope.launch {
            MovimentacaoRepository.getMovimentations(id)
                .flowOn(Dispatchers.Main)
                .onStart {
                    _state.value = State.Loading
                }
                .catch {
                    _state.value = State.Error(it.message.toString())
                }
                .collect {
                    _state.value = State.Success(it)
                }

        }
    }

    sealed class State{
        data class Success(val data: List<Movimentacao>) : State()
        data class Error(val error: String): State()
        object Loading: State()
    }
}
