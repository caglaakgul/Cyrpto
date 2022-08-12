package com.example.cryptocurrencypricetrackerapp.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cryptocurrencypricetrackerapp.R
import com.example.cryptocurrencypricetrackerapp.base.BaseFragment
import com.example.cryptocurrencypricetrackerapp.databinding.FragmentDetailBinding
import com.example.cryptocurrencypricetrackerapp.databinding.FragmentHomeBinding
import com.example.cryptocurrencypricetrackerapp.viewmodel.DetailViewModel
import com.example.cryptocurrencypricetrackerapp.viewmodel.HomeViewModel
import com.example.cryptocurrencypricetrackerapp.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class DetailFragment : BaseFragment() {
    private val args: DetailFragmentArgs by navArgs()

    lateinit var binding: FragmentDetailBinding

    @Inject
    lateinit var viewModel: DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelOf(DetailViewModel::class.java)
        findNavController()
        viewModel.callSearchList(args.id?:"")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeOn()
    }

    private fun observeOn(){
        viewModel.coinDetailResponse.observe(viewLifecycleOwner, Observer { coinDetailResponse ->
            coinDetailResponse.let {
                binding.detailViewModel = coinDetailResponse
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            if (loading) showProgress()
            else dismissProgress()
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as AppCompatActivity).textToolbar.text = "Detay"
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

}