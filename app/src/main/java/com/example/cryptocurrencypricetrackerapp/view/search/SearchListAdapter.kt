package com.example.cryptocurrencypricetrackerapp.view.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencypricetrackerapp.R
import com.example.cryptocurrencypricetrackerapp.databinding.ItemSearchListBinding
import com.example.cryptocurrencypricetrackerapp.model.data.Coins

class SearchListAdapter(var clickListener: OnSearchListItemClickListener, private val list: ArrayList<Coins>,
) : RecyclerView.Adapter<SearchListAdapter.SearchListItemViewHolder>() {

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListItemViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null

        val binding: ItemSearchListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_search_list,
            parent,
            false
        )
        viewHolder = SearchListItemViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: SearchListItemViewHolder, position: Int) {
        holder.init(list[position], clickListener)
        holder.binding.coinModel = list[position]
    }

    class SearchListItemViewHolder(val binding: ItemSearchListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun init(item: Coins, action: OnSearchListItemClickListener) {
            binding.coinModel = item
            binding.root.setOnClickListener {
                action.onAppreciateListAItemClickListener(item, adapterPosition)
            }
        }
    }

    fun updateList(newList: List<Coins>) {
        list.addAll(newList)
        notifyDataSetChanged()
    }

    interface OnSearchListItemClickListener {
        fun onAppreciateListAItemClickListener(item: Coins, position: Int)
    }

}