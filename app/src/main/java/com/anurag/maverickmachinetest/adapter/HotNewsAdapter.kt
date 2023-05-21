package com.anurag.maverickmachinetest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anurag.maverickmachinetest.databinding.AdapterHotNewsBinding
import com.anurag.maverickmachinetest.model.api.data.hotNews.HotNews
import com.anurag.maverickmachinetest.utils.showImage

class HotNewsAdapter: RecyclerView.Adapter<HotNewsAdapter.ViewHolder>() {

    private var newsList: MutableList<HotNews> = mutableListOf()

    fun setNews(news: MutableList<HotNews>){

        val diffResult = DiffUtil.calculateDiff(ItemDiffCallback(newsList, news))
        newsList.clear()
        newsList.addAll(news)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val adapterHotNewsBinding = AdapterHotNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(adapterHotNewsBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setBindingData(newsList[position])
    }

    override fun getItemCount(): Int {

        return newsList.size
    }

    class ViewHolder(private val binding: AdapterHotNewsBinding): RecyclerView.ViewHolder(binding.root) {

        fun setBindingData(news: HotNews){

            binding.news = news
            binding.ivNewsImage.showImage(news.newsImage)
            binding.executePendingBindings()
        }
    }

    class ItemDiffCallback(private val oldList: List<HotNews>,
                           private val newList: List<HotNews>): DiffUtil.Callback(){

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
}