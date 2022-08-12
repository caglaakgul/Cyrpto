package com.example.cryptocurrencypricetrackerapp.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencypricetrackerapp.R
import com.example.cryptocurrencypricetrackerapp.base.BaseFragment
import com.example.cryptocurrencypricetrackerapp.databinding.FragmentHomeBinding
import com.example.cryptocurrencypricetrackerapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeFragment : BaseFragment() {
    lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var viewModel: HomeViewModel

    private var coinListAdapter = CoinListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelOf(HomeViewModel::class.java)
        findNavController()

        viewModel.callCoinList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewHome.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            binding.recyclerViewHome.layoutManager = layoutManager
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = coinListAdapter
        }

        observeOn()
    }

    private fun observeOn(){
        viewModel.coinListResponse.observe(viewLifecycleOwner, Observer { coinListResponse ->
            coinListResponse.let {
               coinListAdapter.updateList(coinListResponse)
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            if (loading) showProgress()
            else dismissProgress()
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
        (activity as AppCompatActivity).bottomNavigationBar.visibility = View.VISIBLE
        (activity as AppCompatActivity).toolbar.visibility = View.GONE

    }
}