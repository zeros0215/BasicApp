package com.study.basicapp.ui.remotelist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.study.basicapp.common.BaseFragment
import com.study.basicapp.databinding.FragmentLocallistBinding
import com.study.basicapp.repository.UserTableRespository
import com.study.basicapp.ui.remotelist.model.user_item
import com.study.basicapp.ui.locallist.LocalListViewModel
import com.study.basicapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LocalListFragment : BaseFragment(){

    private val TAG = "LocalListFragment"
    private lateinit var binding : FragmentLocallistBinding

    private var localListAdapter : LocalListAdapter? = null
    //    private lateinit var localListViewModel: LocalListViewModel
    //    localListViewModel = ViewModelProvider(this).get(LocalListViewModel::class.java)
    private val localListViewModel: LocalListViewModel by viewModels()

    //    @Inject
    //    lateinit var respository: UserTableRespository
    //    @Inject
    //    lateinit var sharedPreferences: PreferencesHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basiclist, container, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocallistBinding.inflate(inflater, container, false)
        Log.d(TAG, "onCreateView")
        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_locallist, container, false)
        Log.d(TAG, " TIME:  " + Utils.getCurrentTime())

        initViewModel()
        initRecyclerView()
        //initUiControl()
        testOnly()
        return binding.root
    }

    override fun onStart() {
        Log.d(TAG, "onPause")
        super.onStart()
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
    }

    override fun onDetach() {
        Log.d(TAG, "onDetach")
        super.onDetach()
    }

    override fun initViewModel() {
        Log.d(TAG, "initViewModel")
        //localListViewModel = ViewModelProvider(this).get(LocalListViewModel::class.java)
        binding.viewModel = localListViewModel
        binding.viewModel!!.liveData.observe(getViewLifecycleOwner(), Observer {
            localListAdapter!!.setItem(it)
            localListAdapter!!.notifyDataSetChanged()
            Log.d(TAG, "it.size: " + it.size)
        })
    }


    override fun initRecyclerView() {
        var layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        localListAdapter = LocalListAdapter()
        localListAdapter!!.setOnItemClickListener(object :LocalListAdapter.OnItemClickListener{
            override fun OnClick(v: View, position: Int) {
                val items : user_item = localListAdapter!!.getItem(position)
                Toast.makeText(requireContext(), items.toString() , Toast.LENGTH_SHORT).show()
            }

            override fun OnIconClick(v: View, position: Int) {
                Log.d(TAG, "OnIconClick position: " + position)
                val items : user_item = localListAdapter!!.getItem(position)
                Toast.makeText(requireContext(), items.toString() , Toast.LENGTH_SHORT).show()
                localListViewModel.deleteToDbItem(items)
            }

        })

        binding.localList.setLayoutManager(layoutManager)
        binding.localList.setAdapter(localListAdapter)
    }

    override fun initUI() {
        //TODO("Not yet implemented")
    }

    fun testOnly(){
        Utils.getCurrentTime()

    }


}