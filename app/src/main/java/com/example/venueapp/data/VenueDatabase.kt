package com.example.venueapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [VenueData::class], version = 1)
abstract class VenueDatabase: RoomDatabase() {

    abstract fun venueDataDao(): VenueDataDao

    companion object {
        @Volatile
        private var INSTANCE: VenueDatabase? = null

        fun getDataBase(context: Context): VenueDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VenueDatabase::class.java,
                    "venue_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }

    }
}