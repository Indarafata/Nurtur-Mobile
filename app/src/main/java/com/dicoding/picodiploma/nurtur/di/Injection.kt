package com.dicoding.picodiploma.nurtur.di

import android.content.Context
import com.dicoding.picodiploma.nurtur.data.UserRepository
import com.dicoding.picodiploma.nurtur.data.pref.UserPreference
import com.dicoding.picodiploma.nurtur.data.pref.dataStore

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }
}