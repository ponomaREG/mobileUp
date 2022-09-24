package org.disf.app.mobileuptrainee.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.disf.app.mobileuptrainee.data.repository.CoinRepositoryImpl
import org.disf.app.mobileuptrainee.domain.repository.CoinRepository


@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun bindCoordinatesRepository(coinRepository: CoinRepositoryImpl): CoinRepository
}