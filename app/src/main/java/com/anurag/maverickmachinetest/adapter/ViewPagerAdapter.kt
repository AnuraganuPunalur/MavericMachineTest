package com.anurag.maverickmachinetest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anurag.maverickmachinetest.databinding.MainCarousalBinding
import com.anurag.maverickmachinetest.utils.showImage

class ViewPagerAdapter(): RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    private val imageList = arrayListOf("https://www.financialexpress.com/wp-content/uploads/2023/05/rs-2000-notes-3.jpg",
        "https://www.financialexpress.com/wp-content/uploads/2023/05/fixed-deposit-news-1.jpg",
        "https://www.financialexpress.com/wp-content/uploads/2023/05/cci1.jpg",
        "https://www.financialexpress.com/wp-content/uploads/2023/05/lat.jpg")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = MainCarousalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setBinding(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    class ViewHolder(private val mainCarousalBinding: MainCarousalBinding):
        RecyclerView.ViewHolder(mainCarousalBinding.root) {

            fun setBinding(url: String){

             mainCarousalBinding.ivViewPagerBg.showImage(url)
             mainCarousalBinding.executePendingBindings()
            }
    }
}