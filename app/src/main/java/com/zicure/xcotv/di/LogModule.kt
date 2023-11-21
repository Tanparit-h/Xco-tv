package com.zicure.xcotv.di

import com.zicure.xcotv.domain.UatTree
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import timber.log.Timber

@Module
@InstallIn(SingletonComponent::class)
abstract class LogModule {

    @Binds
    abstract fun bindsUatTree(
        uatTree: UatTree
    ): Timber.Tree
}