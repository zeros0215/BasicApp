package com.study.fineme.di.entrypoints

import com.study.basicapp.repository.UserItemRespository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author authorName
 * @created on 2024/07/03
 * @desc
 */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface UserItemRepositoryInterface {
    fun userItemRepository(): UserItemRespository
}
