package com.study.basicapp.ui.detailview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.study.basicapp.ui.detailview.model.DetailViewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel(){

    private val TAG = "DetailViewModel"
    var liveData : MutableLiveData<DetailViewItem> = MutableLiveData<DetailViewItem>()

    fun setViewDetailItem(viewItem: DetailViewItem) {
        liveData.postValue(viewItem)
    }

}