package ucr.ac.cr.ecci.ci1340.sensores.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ucr.ac.cr.ecci.ci1340.sensores.dao.LogDao
import ucr.ac.cr.ecci.ci1340.sensores.dao.SensorDao
import ucr.ac.cr.ecci.ci1340.sensores.entity.Log
import ucr.ac.cr.ecci.ci1340.sensores.entity.Sensor

// Annotates class to be a Room Database with a table (entity) of the Sensor class
@Database(entities = arrayOf(Sensor::class, Log::class), version = 1)
abstract class SensorRoomDatabase : RoomDatabase() {

    abstract fun sensorDao(): SensorDao
    abstract fun logDao(): LogDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SensorRoomDatabase? = null

        fun getDatabase(context: Context): SensorRoomDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SensorRoomDatabase::class.java,
                    "sensores_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}