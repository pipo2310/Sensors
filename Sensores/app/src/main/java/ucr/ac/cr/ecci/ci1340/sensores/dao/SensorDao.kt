package ucr.ac.cr.ecci.ci1340.sensores.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ucr.ac.cr.ecci.ci1340.sensores.entity.Sensor

@Dao
interface SensorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(sensor: Sensor)

    @Query("SELECT * from sensores ORDER BY id ASC")
    fun getAllSensores(): LiveData<List<Sensor>>

    @Update
    fun update(sensor: Sensor)

    @Query("DELETE FROM sensores")
    suspend fun deleteAll()
}