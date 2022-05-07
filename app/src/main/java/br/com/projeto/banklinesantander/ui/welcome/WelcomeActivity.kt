package br.com.projeto.banklinesantander.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.projeto.banklinesantander.core.ACCOUNT_HOLDER_ID
import br.com.projeto.banklinesantander.databinding.ActivityWelcomeBinding
import br.com.projeto.banklinesantander.domain.Correntista
import br.com.projeto.banklinesantander.ui.statement.BankStatementActivity

class WelcomeActivity : AppCompatActivity() {
    val binding: ActivityWelcomeBinding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        SetupContinueButton()
    }

    private fun SetupContinueButton() {
        binding.btContinue.setOnClickListener {
            val id = binding.inputId.text.toString().toInt()
            val accountHolder = Correntista(id)
            val intent = Intent(this, BankStatementActivity::class.java).apply {
                putExtra(ACCOUNT_HOLDER_ID, accountHolder)
            }

            startActivity(intent)
        }
    }
}