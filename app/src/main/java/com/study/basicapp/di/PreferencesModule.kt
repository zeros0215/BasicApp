//package com.study.basicapp.di
//
//import android.content.Context
//import android.content.SharedPreferences
////import com.study.basicapp.common.PreferencesConstants
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object PreferencesModule {
//
//    const val BASIC_APP_PREF = "basicapp_prefs"
//
//    @Provides
//    @Singleton
//    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
//        return context.getSharedPreferences(BASIC_APP_PREF, Context.MODE_PRIVATE)
//    }
//
////    @Provides
////    @Singleton
////    fun providePreferencesHelper(sharedPreferences: SharedPreferences): PreferencesHelper {
////        return PreferencesHelper(sharedPreferences)
////    }
//
//
////    @Provides
////    @Singleton
////    fun providePreferencesRepository(sharedPreferences: SharedPreferences): PreferencesHelper {
////        return providePreferencesRepository(sharedPreferences)
////    }
//
//
//}