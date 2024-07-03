package com.study.basicapp.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.study.basicapp.R
import com.study.basicapp.databinding.FragmentBasiclistBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    //private lateinit var binding : FragmentBasiclistBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basiclist, container, false)
        initViewModel() //For viewmodel
        initRecyclerView() //For recycler view
        initUI()
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    fun onBackPressed(): Boolean {
        return false
    }

    abstract fun initViewModel()
    abstract fun initRecyclerView()
    abstract fun initUI()

}