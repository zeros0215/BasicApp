package com.study.basicapp.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.study.basicapp.R
import com.study.basicapp.databinding.FragmentLocallistBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    //private lateinit var binding : FragmentLocallistBinding
    /*onCreateView: Fragment의 뷰를 생성하고 반환하는 역할. 여기서 레이아웃을 인플레이트합니다.*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding = FragmentLocallistBinding.inflate(inflater, container, false)
        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basiclist, container, false)
        // return binging.root
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /*onViewCreated: 뷰가 생성된 후에 호출되며, 뷰와 관련된 추가적인 초기화 작업을 수행합니다.*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*PARENT에서 두번 호출 되는 경우가 발생함. abstract 함수만 선언하는 방식으로 */
        //        initViewModel() //For viewmodel
        //        initRecyclerView() //For recycler view
        //        initUI()
    }

    fun onBackPressed(): Boolean {
        return false
    }

    abstract fun initViewModel()
    abstract fun initRecyclerView()
    abstract fun initUI()

}