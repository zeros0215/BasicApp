package com.study.basicapp.ui.remotelist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.study.basicapp.R
import com.study.basicapp.common.BaseFragment
import com.study.basicapp.databinding.FragmentBasiclistBinding
import com.study.basicapp.ui.remotelist.model.user_item
import com.study.basicapp.ui.detailview.DetailViewFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RemoteListFragment : BaseFragment(){

    private val TAG = "RemoteListFragment"
    private lateinit var binding : FragmentBasiclistBinding

    private var remoteListAdapter : RemoteListAdapter? = null
//    private lateinit var remoteListViewModel: RemoteListViewModel
//    remoteListViewModel = ViewModelProvider(this).get(RemoteListViewModel::class.java)
    private val remoteListViewModel: RemoteListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basiclist, container, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding = FragmentBasiclistBinding.inflate(inflater, container, false)
        Log.d(TAG, "onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basiclist, container, false)

        initViewModel()
        initRecyclerView()
        return binding.root
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
    }

    override fun onDetach() {
        Log.d(TAG, "onDetach")
        super.onDetach()
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun initViewModel() {
        //remoteListViewModel = ViewModelProvider(this).get(RemoteListViewModel::class.java)
        binding.viewModel = remoteListViewModel

        binding.viewModel!!.liveData.observe(getViewLifecycleOwner(), Observer {
            remoteListAdapter!!.setItem(it)
            remoteListAdapter!!.notifyDataSetChanged()
            Log.d(TAG, "it.size: " + it.size)
        })

    }


    override fun initRecyclerView() {
        var layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        remoteListAdapter = RemoteListAdapter()
        remoteListAdapter!!.setOnItemClickListener(object :RemoteListAdapter.OnItemClickListener{
            override fun OnClick(v: View, position: Int) {
                Log.d(TAG, "OnClick position: " + position)
                val items : user_item = remoteListAdapter!!.getItem(position)
                Toast.makeText(requireContext(), items.toString() , Toast.LENGTH_SHORT).show()

                val bundle = Bundle()
                val detailViewFragment = DetailViewFragment()
                bundle.putString("name", items.name)
                bundle.putString("number", items.number)
                detailViewFragment.arguments = bundle

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, detailViewFragment, detailViewFragment.toString())
                    .addToBackStack(null)
                    .commit()

            }

            override fun OnIconClick(v: View, position: Int) {
                Log.d(TAG, "OnIconClick position: " + position)
                val items : user_item = remoteListAdapter!!.getItem(position)
                Toast.makeText(requireContext(), items.toString() , Toast.LENGTH_SHORT).show()
                remoteListViewModel.isertToDbItem(items)
            }
        })

        binding.basicList.setLayoutManager(layoutManager)
        binding.basicList.setAdapter(remoteListAdapter)
    }

    override fun initUI() {
        //TODO("Not yet implemented")
    }

}