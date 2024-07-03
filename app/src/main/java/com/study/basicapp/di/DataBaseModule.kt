package com.study.basicapp.di

import android.content.Context
import androidx.room.Room
import com.study.basicapp.database.AppDatabase
import com.study.basicapp.database.UserDao
import com.study.basicapp.repository.UserTableRespository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    const val BASIC_APP_DBNAME = "basicapp.db"

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room
        .databaseBuilder(context, AppDatabase::class.java, BASIC_APP_DBNAME)
        .build()

    @Singleton
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userTable()
    }

    @Provides
    @Singleton
    fun provideUserTableRepository(userItem: UserDao): UserTableRespository {
        return UserTableRespository(userItem)
    }

}