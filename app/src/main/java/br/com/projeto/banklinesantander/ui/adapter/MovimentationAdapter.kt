package br.com.projeto.banklinesantander.ui.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.projeto.banklinesantander.R
import br.com.projeto.banklinesantander.databinding.MovimentationItemBinding
import br.com.projeto.banklinesantander.domain.Movimentacao
import br.com.projeto.banklinesantander.domain.TipoMovimentacao

class MovimentationAdapter : ListAdapter<Movimentacao, MovimentationAdapter.MovimentationHolder>(CardComparator()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovimentationHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovimentationItemBinding.inflate(inflater, parent, false)
        return MovimentationHolder(binding)
    }

        override fun onBindViewHolder(holder: MovimentationHolder, position: Int) {
            holder.bindViews(getItem(position))
    }

    class MovimentationHolder(private val binding: MovimentationItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindViews(movimentacao: Movimentacao) {
            with(binding) {
                movimentationDescription.text = movimentacao.descricao
                movimentationValue.text = String.format("R$ %s", movimentacao.valor)
                movimentationDate.text = movimentacao.dataHora

                if(movimentacao.tipoMovimentacao == TipoMovimentacao.DESPESA) {
                    accountDetailImage.setImageResource(
                        R.drawable.ic_negative_24
                    )

                    binding.movimentationValue.setTextColor(ResourcesCompat.getColor(
                        Resources.getSystem(),
                        android.R.color.holo_red_dark,
                        root.context.theme))
                }

            }
        }
    }
}

class CardComparator : DiffUtil.ItemCallback<Movimentacao>() {
    override fun areItemsTheSame(oldItem: Movimentacao, newItem: Movimentacao): Boolean {
        return oldItem == newItem;
    }

    override fun areContentsTheSame(oldItem: Movimentacao, newItem: Movimentacao): Boolean {
        return oldItem.id == newItem.id;
    }

}
