package com.study.basicapp.di.module

import com.study.basicapp.MyApplication
import com.study.basicapp.repository.PreferencesRepository
import com.study.basicapp.repository.RemoteRespository
import com.study.basicapp.repository.UserItemRespository
import com.study.fineme.di.entrypoints.PreferenceRepositoryInterface
import com.study.fineme.di.entrypoints.RemoteRepositoryInterface
import com.study.fineme.di.entrypoints.UserItemRepositoryInterface
import dagger.hilt.EntryPoints

/**
 *
 *
 * 안드로이드 클래스의 경우 @AndroidEntryPoint 를 사용하여 삽입합니다.
 * 그외 클래스의 경우 아래 함수를 이용해 Repository 객체를 받아옵니다.
 *
 *
 */
object DiUtil {
    @JvmStatic
    val userItemRepository: UserItemRespository
        get() {
            val userItemRepositoryInterface =
                EntryPoints.get(MyApplication.applicationContext(), UserItemRepositoryInterface::class.java)
            return userItemRepositoryInterface.userItemRepository()
        }

    @JvmStatic
    val remoteRepository: RemoteRespository
        get() {
            val remoteRepositoryInterface =
                EntryPoints.get(MyApplication.applicationContext(), RemoteRepositoryInterface::class.java)
            return remoteRepositoryInterface.remoteRepository()
        }

    @JvmStatic
    val preferenceRepository: PreferencesRepository
        get() {
            val preferenceRepositoryInterface =
                EntryPoints.get(MyApplication.applicationContext(), PreferenceRepositoryInterface::class.java)
            return preferenceRepositoryInterface.preferenceRepository()
        }

}
