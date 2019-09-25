package ucr.ac.cr.ecci.ci1340.sensores.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ucr.ac.cr.ecci.ci1340.sensores.entity.Log

@Dao
interface LogDao {

    @Query("SELECT * from sensores_logs ORDER BY datetime DESC")
    fun getAllLogs(): LiveData<List<Log>>

    @Query("SELECT * from sensores_logs WHERE id_sensor = :id_sensor ORDER BY datetime DESC")
    fun getLogs(id_sensor: Int): LiveData<List<Log>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(log: Log)

    @Query("DELETE FROM sensores_logs")
    suspend fun deleteAll()
}