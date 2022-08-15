package com.example.venueapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [VenueData::class], version = 1, exportSchema = false)
abstract class VenueDataBase : RoomDatabase() {

    abstract fun venueDataDao(): VenueDataDao

    companion object {
        @Volatile
        private var INSTANCE: VenueDataBase? = null

        fun getDataBase(context: Context): VenueDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VenueDataBase::class.java,
                    "venueDataTable"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }


}