package com.study.basicapp.ui.remotelist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserItem(
    val name: String?,
    val number: String?,
): Parcelable

