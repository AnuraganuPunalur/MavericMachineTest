package com.anurag.maverickmachinetest.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.anurag.maverickmachinetest.R
import com.anurag.maverickmachinetest.adapter.FundsAdapter
import com.anurag.maverickmachinetest.adapter.HotNewsAdapter
import com.anurag.maverickmachinetest.adapter.ViewPagerAdapter
import com.anurag.maverickmachinetest.databinding.ActivityMainBinding
import com.anurag.maverickmachinetest.model.api.data.ApiResponse
import com.anurag.maverickmachinetest.model.api.data.hotNews.HotNews
import com.anurag.maverickmachinetest.model.api.data.hotNews.HotNewsResponse
import com.anurag.maverickmachinetest.model.api.data.topFunds.TopFund
import com.anurag.maverickmachinetest.model.api.data.topFunds.TopFundsResponse
import com.anurag.maverickmachinetest.utils.AppUtils
import com.anurag.maverickmachinetest.utils.showToast
import com.anurag.maverickmachinetest.viewmodel.MainViewModel

class MainActivity : BaseActivity(), View.OnClickListener, FundsAdapter.BuyClickListener {

    private lateinit var hotNewsAdapter: HotNewsAdapter
    private lateinit var fundsAdapter: FundsAdapter
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        doInitialSetup()
        retrieveTopFunds(this)
        retrieveHotNews(this)
    }

    override fun doInitialSetup() {

        mainViewModel = ViewModelProvider(this)[MainViewModel(application)::class.java]
        activityMainBinding.tvMore.setOnClickListener(this)
        activityMainBinding.ivScan.setOnClickListener(this)
        activityMainBinding.ivPay.setOnClickListener(this)
        activityMainBinding.ivAccount.setOnClickListener(this)
        activityMainBinding.ivTransfer.setOnClickListener(this)

        hotNewsAdapter = HotNewsAdapter()
        fundsAdapter = FundsAdapter(this)
        activityMainBinding.recyclerTopFunds.apply {

            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = fundsAdapter
        }
        activityMainBinding.recyclerHotNews.apply {

            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = hotNewsAdapter
        }

        activityMainBinding.trendingOffersViewPager.adapter = ViewPagerAdapter()
//        activityMainBinding.trendingOffersViewPager.registerOnPageChangeCallback(ViewPager2.OnPageChangeCallback{
//
//
//        })
    }

    override fun onClick(v: View?) {

        when(v?.id){

            R.id.ivScan -> showToast(getString(R.string.scan))
            R.id.ivPay -> showToast(getString(R.string.pay))
            R.id.ivAccount -> showToast(getString(R.string.account))
            R.id.ivTransfer -> showToast(getString(R.string.transfer))
            R.id.tvMore -> showToast("More funds will be displayed")
        }
    }

    private fun retrieveTopFunds(context: Context){

        if (AppUtils.isNetworkAvailable(context)){

            mainViewModel.retrieveTopFunds().observe(this){

                response ->
                when(response){
                    is ApiResponse.Error -> showToast(response.message)
                    ApiResponse.Loading -> showToast("Loading")
                    is ApiResponse.Success -> {

                        val topFunds = response.data as TopFundsResponse
                        if (topFunds.topFunds.isNotEmpty()){

                            fundsAdapter.setFundData(topFunds.topFunds as MutableList<TopFund>)
                        }
                    }
                }
            }
        }else{

            showToast(getString(R.string.no_internet))
        }
    }

     private fun retrieveHotNews(context: Context){

         if (AppUtils.isNetworkAvailable(context)){

            mainViewModel.retrieveHotNews().observe(this) {

                response ->
                when(response){

                    is ApiResponse.Error -> showToast(response.message)
                    ApiResponse.Loading -> showToast("Loading")
                    is ApiResponse.Success -> {

                        val hotNewsData = response.data as HotNewsResponse
                        hotNewsAdapter.setNews(hotNewsData.hotNews as MutableList<HotNews>)
                    }
                }
            }
         }else{

             showToast(getString(R.string.no_internet))
         }
     }

    override fun onClicked(id: String) {

        showToast("Will go to fund buying screen")
    }

}