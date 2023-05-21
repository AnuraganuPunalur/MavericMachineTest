package com.anurag.maverickmachinetest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anurag.maverickmachinetest.databinding.AdapterFundsBinding
import com.anurag.maverickmachinetest.model.api.data.topFunds.TopFund

class FundsAdapter(private val buyClickListener: BuyClickListener): RecyclerView.Adapter<FundsAdapter.ViewHolder>() {

    private var fundData: MutableList<TopFund> = mutableListOf()

    fun setFundData(fund: MutableList<TopFund>){

        val diffResult = DiffUtil.calculateDiff(ItemDiffCallback(fundData, fund))
        fundData.clear()
        fundData.addAll(fund)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val adapterFundsBinding = AdapterFundsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(adapterFundsBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setFundData(fundData[position])
        holder.binding.btBuyFund.setOnClickListener {

            buyClickListener.onClicked(fundData[position].id.toString())
        }
    }

    override fun getItemCount(): Int {

        return fundData.size
    }

    class ViewHolder(private val adapterFundsBinding: AdapterFundsBinding): RecyclerView.ViewHolder(adapterFundsBinding.root) {

        val binding = adapterFundsBinding
        fun setFundData(fund: TopFund){

            adapterFundsBinding.fund = fund
            adapterFundsBinding.executePendingBindings()
        }
    }

    class ItemDiffCallback(private val oldList: List<TopFund>,
                           private val newList: List<TopFund>): DiffUtil.Callback(){

        override fun getOldListSize(): Int {

            return oldList.size
        }

        override fun getNewListSize(): Int {

            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    interface BuyClickListener{

        fun onClicked(id: String)
    }
}