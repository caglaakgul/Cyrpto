package com.example.cryptocurrencypricetrackerapp.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencypricetrackerapp.R
import com.example.cryptocurrencypricetrackerapp.base.BaseFragment
import com.example.cryptocurrencypricetrackerapp.databinding.FragmentLoginBinding
import com.example.cryptocurrencypricetrackerapp.databinding.FragmentSearchBinding
import com.example.cryptocurrencypricetrackerapp.model.data.Coins
import com.example.cryptocurrencypricetrackerapp.view.home.CoinListAdapter
import com.example.cryptocurrencypricetrackerapp.viewmodel.LoginViewModel
import com.example.cryptocurrencypricetrackerapp.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class SearchFragment : BaseFragment(), SearchListAdapter.OnSearchListItemClickListener{
    lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var viewModel: SearchViewModel

    private var searchListAdapter = SearchListAdapter(this, arrayListOf())

    var textQuery: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelOf(SearchViewModel::class.java)
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI(binding.root)

        binding.recyclerViewSearchList.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            binding.recyclerViewSearchList.layoutManager = layoutManager
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = searchListAdapter
        }

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.callSearchList(query ?: "")

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                textQuery = newText.toString()

                if(textQuery.isNotEmpty()){
                    if(textQuery.length >= 3){
                        binding.recyclerViewSearchList.visibility = View.VISIBLE
                        viewModel.callSearchList(textQuery ?: "")
                    } else{
                        binding.recyclerViewSearchList.visibility = View.GONE
                    }
                } else{
                    binding.recyclerViewSearchList.visibility = View.GONE
                }

                return false
            }
        })

        observeOn()
    }

    private fun observeOn(){
        viewModel.searchListResponse.observe(viewLifecycleOwner, Observer { searchListResponse ->
            searchListResponse.let {
                searchListAdapter.updateList(searchListResponse.coins)
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

    override fun onAppreciateListAItemClickListener(item: Coins, position: Int) {
        val bundle = bundleOf("id" to item.id)

        findNavController().navigate(R.id.action_global_detailFragmentDest, bundle)

    }

}