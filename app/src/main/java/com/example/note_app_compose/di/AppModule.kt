package com.example.note_app_compose.di

import com.example.note_app_compose.data.remote.NoteApi
import com.example.note_app_compose.data.repository.NoteRepositoryImpl
import com.example.note_app_compose.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteApi(): NoteApi {
        return Retrofit.Builder()
            .baseUrl("https://note-app-backend-nodejs-production.up.railway.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NoteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(api: NoteApi): NoteRepository {
        return NoteRepositoryImpl(api)
    }
}
