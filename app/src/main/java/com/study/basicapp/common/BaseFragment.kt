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
    /*onCreateView: Fragment�� �並 �����ϰ� ��ȯ�ϴ� ����. ���⼭ ���̾ƿ��� ���÷���Ʈ�մϴ�.*/
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

    /*onViewCreated: �䰡 ������ �Ŀ� ȣ��Ǹ�, ��� ���õ� �߰����� �ʱ�ȭ �۾��� �����մϴ�.*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*PARENT���� �ι� ȣ�� �Ǵ� ��찡 �߻���. abstract �Լ��� �����ϴ� ������� */
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