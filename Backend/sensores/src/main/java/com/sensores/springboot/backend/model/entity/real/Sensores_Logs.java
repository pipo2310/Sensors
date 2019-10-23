package com.sensores.springboot.backend.model.entity.real;

import com.sensores.springboot.backend.model.entity.foo.Medicion;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;


@SqlResultSetMapping(
        name = "mapeoMeses",
        classes = {
                @ConstructorResult(targetClass = Medicion.class,
                columns = {
                        @ColumnResult(name = "Month"),
                        @ColumnResult(name = "Promedio")
                })
        }
)

@NamedNativeQuery(name = "Sensores_Logs.obtenerMeses" , query = "select to_char(date_time, 'Month' ) as Month,avg(sl.valor) as Promedio " +
        "        from sensores_logs as sl join sensores s on sl.id_sensor = s.sensores_pk" +
        "        where date_trunc('day', now() - interval '1 year') <= sl.date_time AND s.tipo = :tipo GROUP BY Month", resultSetMapping = "mapeoMeses")



@SqlResultSetMapping( name = "mapeoSemanas",
        classes = {
                @ConstructorResult(targetClass = Medicion.class,
                        columns = {
                                @ColumnResult(name = "Week"),
                                @ColumnResult(name = "Promedio")
                        })
        })
@NamedNativeQuery(name = "Sensores_Logs.obtenerSemanas", query = "select  to_char(date_time , 'Week' ) as Week,avg(sl.valor) as Promedio\n" +
        "        from sensores_logs as sl join sensores s on sl.id_sensor = s.sensores_pk\n" +
        "        where date_trunc('day', now() - interval '1 month') <= sl.date_time\n" +
        "        AND s.tipo = :tipo  GROUP BY Week" , resultSetMapping = "mapeoSemanas")


@SqlResultSetMapping( name = "mapeoDias",
        classes = {
                @ConstructorResult(targetClass = Medicion.class,
                        columns = {
                                @ColumnResult(name = "Day"),
                                @ColumnResult(name = "Promedio")
                        })
        })
@NamedNativeQuery(name = "Sensores_Logs.obtenerDias", query = "select  to_char(\"date_time\" , 'Day' ) as Day,avg(sl.valor) as Promedio" +
        "        from sensores_logs as sl join sensores s on sl.id_sensor = s.sensores_pk" +
        "        where date_trunc('day', now() - interval '1 week') <= sl.date_time" +
        "        AND s.tipo = :tipo  GROUP BY day" , resultSetMapping = "mapeoDias")





@Entity
@Table(name = "sensores_logs" , schema = "public")
//@NamedStoredProcedureQueries
public class Sensores_Logs implements Serializable {

    public Sensores_Logs(){}

    @EmbeddedId
    private Sensores_logs_pk sensoresLogsPk;

    @Column ( name = "valor")
    private double valor;

    public Sensores_logs_pk getSensoresLogsPk() {
        return sensoresLogsPk;
    }

    public void setSensoresLogsPk(Sensores_logs_pk sensoresLogsPk) {
        this.sensoresLogsPk = sensoresLogsPk;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    /*
    * Relación entre sensores Log y sensores
    * */
    @MapsId("sensores_id")
    @JoinColumns({
            @JoinColumn(name = "id_sensor", referencedColumnName = "sensores_pk")
    })
    @ManyToOne
    private Sensores sensores;
}
