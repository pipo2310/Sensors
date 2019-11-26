package com.sensores.springboot.backend.model.entity.real;



import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.swing.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


@Embeddable
public class Sensores_logs_pk implements Serializable {

    public Sensores_logs_pk(){}

    /*public Sensores_logs_pk(Long id_sensor, Timestamp dateTime) {
        this.id_sensor = id_sensor;
        this.dateTime = dateTime;
    }*/

    public Sensores_logs_pk(Long id_sensor) {
        this.id_sensor = id_sensor;
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        this.dateTime = ts;
    }


    @Column ( name = "id_sensor")
    private Long id_sensor;

    @Column ( name = "dateTime"  , nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp dateTime;




    public Long getId_sensor() {
        return id_sensor;
    }

    public void setId_sensor(Long id_sensor) {
        this.id_sensor = id_sensor;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }
}
