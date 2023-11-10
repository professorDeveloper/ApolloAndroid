package com.azamovhudstc.graphqlanilist.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.azamovhudstc.graphqlanilist.BuildConfig
import com.azamovhudstc.graphqlanilist.data.local.AppDatabase
import com.azamovhudstc.graphqlanilist.data.local.EpisodeDao
import com.azamovhudstc.graphqlanilist.data.local.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PersistenceModule {

    private const val DATABASE_NAME = "anime_database"
    private const val SHARED_PREFERENCES_NAME = BuildConfig.APPLICATION_ID + ".LocalStorage"

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application
    ): AppDatabase = Room
        .databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME)
        .fallbackToDestructiveMigration()
//            .addTypeConverter(typeResponseConverter)
        .build()

    @Provides
    @Singleton
    fun providesEpisodeDao(appDatabase: AppDatabase): EpisodeDao = appDatabase.episodeDao()

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideSettings(
        @ApplicationContext context: Context,
        preferences: SharedPreferences
    ) = Settings(context, preferences)
}