package br.com.projeto.banklinesantander.ui.statement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import br.com.projeto.banklinesantander.R
import br.com.projeto.banklinesantander.core.ACCOUNT_HOLDER_ID
import br.com.projeto.banklinesantander.data.repository.MovimentacaoRepository
import br.com.projeto.banklinesantander.databinding.ActivityBankStatementBinding
import br.com.projeto.banklinesantander.domain.Correntista
import br.com.projeto.banklinesantander.domain.Movimentacao
import br.com.projeto.banklinesantander.domain.TipoMovimentacao
import br.com.projeto.banklinesantander.presentation.MainPresentationViewModel
import br.com.projeto.banklinesantander.ui.adapter.MovimentationAdapter
import java.time.LocalDateTime
import java.util.*

class BankStatementActivity : AppCompatActivity() {

    private val binding: ActivityBankStatementBinding by lazy {
        ActivityBankStatementBinding.inflate(layoutInflater)
    }

    private val adapter: MovimentationAdapter by lazy {
        MovimentationAdapter()
    }

    private val viewModel by viewModels<MainPresentationViewModel>()

    private var correntista: Correntista? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

       /* val dataSet = Arrays.asList(
            Movimentacao(1, "15/07/2022", "teste 1", 1200.00, TipoMovimentacao.DESPESA, 1),
            Movimentacao(2, "15/07/2022", "teste 2", 560.35, TipoMovimentacao.RECEITA, 1),
            Movimentacao(3, "15/07/2022", "teste 3", 800.00, TipoMovimentacao.RECEITA, 1),
            Movimentacao(4, "15/07/2022", "teste 4", 5000.00, TipoMovimentacao.DESPESA, 1),
            Movimentacao(5, "15/07/2022", "teste 5", 1200.00, TipoMovimentacao.RECEITA, 1)
        )

        configRecyclerview(dataSet)*/

        retrievedAccountHolder()
    }

    private fun configRecyclerview(dataSet: List<Movimentacao>?) {
        adapter.submitList(dataSet)

        binding.movimentationsRecyclerview.setHasFixedSize(true)
        binding.movimentationsRecyclerview.adapter = adapter
    }

    private fun retrievedAccountHolder() {
        if (intent.hasExtra(ACCOUNT_HOLDER_ID))
            correntista = intent.getParcelableExtra(ACCOUNT_HOLDER_ID)

        viewModel.getMovimentations(correntista?.id ?: 0)

        viewModel.state.observe(this) { state ->
            when(state) {
                is MainPresentationViewModel.State.Loading -> {
                   binding.swipe.isRefreshing = true
                }
                is MainPresentationViewModel.State.Success -> {
                    configRecyclerview(state.data)
                    binding.swipe.isRefreshing = false
                }
                is MainPresentationViewModel.State.Error -> binding.swipe.isRefreshing = false
            }
        }
    }
}