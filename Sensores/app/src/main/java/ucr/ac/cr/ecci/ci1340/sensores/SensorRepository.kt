package ucr.ac.cr.ecci.ci1340.sensores

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class SensorRepository(private val sensorDao: SensorDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allSensores: LiveData<List<Sensor>> = sensorDao.getAllSensores()

    // The suspend modifier tells the compiler that this must be called from a
    // coroutine or another suspend function.
    suspend fun insert(sensor: Sensor) {
        sensorDao.insert(sensor)
    }

    fun update(sensor: Sensor) {
        sensorDao.update(sensor)
    }
}