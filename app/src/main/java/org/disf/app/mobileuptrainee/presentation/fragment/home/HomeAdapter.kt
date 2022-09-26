package org.disf.app.mobileuptrainee.presentation.fragment.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.disf.app.mobileuptrainee.R
import org.disf.app.mobileuptrainee.databinding.ItemCryptoBinding
import org.disf.app.mobileuptrainee.domain.model.CoinsMarket
import org.disf.app.mobileuptrainee.presentation.ext.load

class HomeAdapter constructor(
    private val onItemClickedListener: (CoinsMarket) -> Unit = {},
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val items: MutableList<CoinsMarket> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(ItemCryptoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int): Unit = with(holder.viewBinding) {
        val item = items[position]
        root.setOnClickListener { onItemClickedListener.invoke(item) }
        cryptoNameTv.text = item.name
        cryptoCostTv.text = root.context.getString(R.string.labeled_cost, item.costCurrency.symbol, item.currentPrice.toString())
        cryptoIconIv.load(item.image)
        cryptoShortNameTv.text = item.symbol
        item.priceChangePercentage24h?.let {
            if (it > 0) {
                cryptoDifferenceTv.text = root.context.getString(R.string.price_change_plus, item.priceChangePercentage24h.toString())
                cryptoDifferenceTv.setTextColor(root.context.getColor(R.color.green))
            }
            else {
                cryptoDifferenceTv.text = root.context.getString(R.string.price_change, item.priceChangePercentage24h.toString())
                cryptoDifferenceTv.setTextColor(root.context.getColor(R.color.red))
            }
        }
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<CoinsMarket>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(
        val viewBinding: ItemCryptoBinding
    ) : RecyclerView.ViewHolder(viewBinding.root)
}