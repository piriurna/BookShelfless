package com.piriurna.bookshelfless.presentation.di

import com.piriurna.bookshelfless.data.BooksApiService
import com.piriurna.bookshelfless.data.BooksApiSource
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBooksApiService(retrofit: Retrofit): BooksApiService {
        return retrofit.create(BooksApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideBooksApiSource(booksApiService: BooksApiService): BooksApiSource {
        return BooksApiSource(booksApiService)
    }

    @Singleton
    @Provides
    fun provideHttpClient() : OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)


        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()
    }
}
