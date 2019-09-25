package ucr.ac.cr.ecci.ci1340.sensores
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sensores_logs")
data class Log(
    @PrimaryKey val id_sensor: Int,
    val valor: Float?,
    val datetime: String?
)