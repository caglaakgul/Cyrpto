package com.example.cryptocurrencypricetrackerapp.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencypricetrackerapp.R
import com.example.cryptocurrencypricetrackerapp.databinding.ItemCoinListBinding
import com.example.cryptocurrencypricetrackerapp.model.CoinListResponse
import com.example.cryptocurrencypricetrackerapp.model.CoinDetailResponse

class CoinListAdapter(private val list: ArrayList<CoinListResponse>,
) : RecyclerView.Adapter<CoinListAdapter.CoinListItemViewHolder>() {

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListItemViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null

        val binding: ItemCoinListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_coin_list,
            parent,
            false
        )
        viewHolder = CoinListItemViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: CoinListItemViewHolder, position: Int) {
        holder.init(list[position])
        holder.binding.coinModel = list[position]
    }

    class CoinListItemViewHolder(val binding: ItemCoinListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun init(item: CoinListResponse) {
            binding.coinModel = item
            binding.root.setOnClickListener {
            }
        }
    }

    fun updateList(newList: List<CoinListResponse>) {
        list.addAll(newList)
        notifyDataSetChanged()
    }

}