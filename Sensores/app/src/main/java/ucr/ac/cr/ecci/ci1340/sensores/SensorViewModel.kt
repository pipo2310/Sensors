package ucr.ac.cr.ecci.ci1340.sensores

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ucr.ac.cr.ecci.ci1340.sensores.database.SensorRoomDatabase
import ucr.ac.cr.ecci.ci1340.sensores.entity.Sensor
import ucr.ac.cr.ecci.ci1340.sensores.repository.SensorRepository

// Class extends AndroidViewModel and requires application as a parameter.
class SensorViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: SensorRepository
    // LiveData gives us updated Sensors when they change.
    val allSensores: LiveData<List<Sensor>>

    init {
        // Gets reference to SensorDao from SensorRoomDatabase to construct
        // the correct SensorRepository. 
        val sensoresDao = SensorRoomDatabase.getDatabase(application).sensorDao()
        repository = SensorRepository(sensoresDao)
        allSensores = repository.allSensores
    }

    // The implementation of insert() is completely hidden from the UI.
    // We don't want insert to block the main thread, so we're launching a new
    // coroutine. ViewModels have a coroutine scope based on their lifecycle called
    // viewModelScope which we can use here.
    fun insert(sensor: Sensor) = viewModelScope.launch {
        repository.insert(sensor)
    }
}