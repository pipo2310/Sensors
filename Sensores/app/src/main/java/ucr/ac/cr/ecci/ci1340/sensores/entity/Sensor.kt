package ucr.ac.cr.ecci.ci1340.sensores.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sensores")
data class Sensor(
    @PrimaryKey val id: Int,
    val tipo: String?,
    val unidad: String?,
    val id_cuenta: Int?,
    val alerta_amarilla: Float?,
    val alerta_roja: Float?
)