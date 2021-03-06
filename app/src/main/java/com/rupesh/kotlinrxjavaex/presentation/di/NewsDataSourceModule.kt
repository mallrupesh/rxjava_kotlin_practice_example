package com.rupesh.kotlinrxjavaex.presentation.di

import com.rupesh.kotlinrxjavaex.data.news.db.NewsDao
import com.rupesh.kotlinrxjavaex.data.news.db.NewsSavedDao
import com.rupesh.kotlinrxjavaex.data.news.repository.dataSource.INewsLocalDataSource
import com.rupesh.kotlinrxjavaex.data.news.repository.dataSource.INewsRemoteDataSource
import com.rupesh.kotlinrxjavaex.data.news.repository.dataSourceImpl.NewsLocalDataSourceImpl
import com.rupesh.kotlinrxjavaex.data.news.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import com.rupesh.kotlinrxjavaex.data.news.service.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NewsDataSourceModule {

    @Singleton
    @Provides
    fun provideINewsRemoteDataSource(
        newsService: NewsService
    ): INewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsService)
    }

    @Singleton
    @Provides
    fun provideINewsLocalDataSource(
        newsDao: NewsDao,
        newsSavedDao: NewsSavedDao
    ): INewsLocalDataSource {
        return NewsLocalDataSourceImpl(newsDao, newsSavedDao)
    }
}