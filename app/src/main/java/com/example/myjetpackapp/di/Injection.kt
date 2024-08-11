package com.example.myjetpackapp.di

import com.example.myjetpackapp.data.Repository

object Injection {
    fun provideRepository(): Repository {
        return Repository.getInstance()
    }
}