package com.sajjad.feature_events.di

import com.sajjad.feature_events.data.api.EventsApi
import com.sajjad.feature_events.data.repository.EventsRepositoryImpl
import com.sajjad.feature_events.domain.repository.EventsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class EventsBindModule {
    @Binds
    abstract fun bindEventsRepository(
        impl: EventsRepositoryImpl
    ): EventsRepository
}

@Module
@InstallIn(SingletonComponent::class)
object EventsProvideModule {
    @Provides
    @Singleton
    fun provideEventsApi(retrofit: Retrofit): EventsApi =
        retrofit.create(EventsApi::class.java)
}
