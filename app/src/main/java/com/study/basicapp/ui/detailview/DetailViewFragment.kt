package com.study.basicapp.ui.detailview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.study.basicapp.R
import com.study.basicapp.common.BaseFragment
import com.study.basicapp.databinding.FragmentDetailviewBinding
import com.study.basicapp.ui.detailview.model.DetailViewItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailViewFragment : BaseFragment(){

    private val TAG = "DetailViewFragment"
    private lateinit var binding : FragmentDetailviewBinding
    private lateinit var viewItem : DetailViewItem
    private lateinit var detailViewModel : DetailViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detailview, container, false)
        binding = FragmentDetailviewBinding.inflate(inflater, container, false)
        initViewModel()
        initUI()
        return binding.root
    }

    override fun initViewModel() {
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding.viewModel = detailViewModel

        binding.viewModel?.liveData?.observe(viewLifecycleOwner, Observer {
            binding.name.setText(it.name)
            binding.number.setText(it.number)
        })


    }

    override fun initRecyclerView() {
        //TODO("Not yet implemented")
    }

    override fun initUI() {
        val name = arguments?.getString("name").toString()
        val number = arguments?.getString("number").toString()
        detailViewModel.setViewDetailItem(DetailViewItem(name, number))
    }


}