package org.disf.app.mobileuptrainee.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import org.disf.app.mobileuptrainee.data.api.CoinAPI
import org.disf.app.mobileuptrainee.data.config.NetworkConfig
import org.disf.app.mobileuptrainee.data.ext.addClient
import org.disf.app.mobileuptrainee.data.ext.addJsonConverter
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [RepositoryModule::class])
object NetworkModule {

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(NetworkConfig.BASE_URL)
        .addJsonConverter()
        .addClient()
        .build()

    @Singleton
    @Provides
    fun provideCoinApi(retrofit: Retrofit): CoinAPI =
        retrofit.create(CoinAPI::class.java)
}