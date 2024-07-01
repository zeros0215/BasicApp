package com.study.basicapp.ui.detailview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.study.basicapp.ui.detailview.model.DetailViewItem

class DetailViewModel : ViewModel(){

    private val TAG = "DetailViewModel"
    var liveData : MutableLiveData<DetailViewItem> = MutableLiveData<DetailViewItem>()

    fun setViewDetailItem(viewItem: DetailViewItem) {
        liveData.postValue(viewItem)
    }


}