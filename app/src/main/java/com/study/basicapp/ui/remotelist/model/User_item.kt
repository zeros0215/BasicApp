package com.study.basicapp.ui.remotelist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class user_item(
    val name: String?,
    val number: String?,
): Parcelable

