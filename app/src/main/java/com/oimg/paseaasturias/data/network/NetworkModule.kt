package com.oimg.paseaasturias.data.network

import com.oimg.paseaasturias.data.RepositoryImpl
import com.oimg.paseaasturias.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Modulo para inyectar
 * */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //direccion
    private val BASE_URL = "http://orion.edv.uniovi.es/~arias/json/" // puede faltar un / pero no se
    @Provides
    @Singleton //para crear una sola vez en ves de varias veces
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    // Interceptor para ayudar que se mando y que se recibio nos ayuda para problemas futuros
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()
    }


    @Provides
    fun providePaseaAsturiasAPIService(retrofit: Retrofit): PaseaAsturiasAPIService {
        return retrofit.create(PaseaAsturiasAPIService::class.java)
    } // dagger llama automaticamente a nuestra funcion retrofit por nosotros

    @Provides
    fun provideRepository(apiService: PaseaAsturiasAPIService): Repository {
        return RepositoryImpl(apiService)
    }


}