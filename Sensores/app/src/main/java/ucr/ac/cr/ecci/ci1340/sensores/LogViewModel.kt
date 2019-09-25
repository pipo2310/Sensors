package ucr.ac.cr.ecci.ci1340.sensores

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class LogViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: LogRepository
    // LiveData gives us updated Sensors when they change.
    val allLogs: LiveData<List<Log>>


    init {
        // Gets reference to logDao from SensorRoomDatabase to construct
        // the correct LogRepository.
        val logsDao = SensorRoomDatabase.getDatabase(application).logDao()
        repository = LogRepository(logsDao)
        allLogs = repository.allLogs

    }

    fun getLogs(id_sensor: Int) {
        repository.getLogs(id_sensor)
    }

    // The implementation of insert() is completely hidden from the UI.
    // We don't want insert to block the main thread, so we're launching a new
    // coroutine. ViewModels have a coroutine scope based on their lifecycle called
    // viewModelScope which we can use here.
    fun insert(log: Log) = viewModelScope.launch {
        repository.insert(log)
    }
}