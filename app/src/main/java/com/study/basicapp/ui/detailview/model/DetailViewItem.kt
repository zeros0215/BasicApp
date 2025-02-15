package com.study.basicapp.ui.detailview.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DetailViewItem(
    val name: String?,
    val number: String?,
): Parcelable
