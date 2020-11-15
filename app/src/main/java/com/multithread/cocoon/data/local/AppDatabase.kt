package com.multithread.cocoon.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.multithread.cocoon.BuildConfig
import com.multithread.cocoon.data.model.localEntity.TopStoryLocalEntity


@Database(
    entities = [(TopStoryLocalEntity::class)],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun provideNewsDao(): NewsDao

    companion object {
        fun create(context: Context): AppDataBase =
            Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "cocoon"
            ).allowMainThreadQueries().build()

        fun getTestInstance(context: Context) =
            Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java)
                .allowMainThreadQueries()
                .build()
    }

}