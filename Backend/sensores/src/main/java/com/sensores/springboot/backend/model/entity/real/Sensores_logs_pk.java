package com.sensores.springboot.backend.model.entity.real;

import javax.persistence.*;
import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Embeddable
public class Sensores_logs_pk implements Serializable {

    public Sensores_logs_pk(){}

    public Sensores_logs_pk(Long id_sensor, LocalDateTime dateTime) {
        this.id_sensor = id_sensor;
        this.dateTime = dateTime;
    }

    @Column ( name = "id_sensor")
    private Long id_sensor;

    @Column ( name = "dateTime" )
    private LocalDateTime dateTime;

    public Long getId_sensor() {
        return id_sensor;
    }

    public void setId_sensor(Long id_sensor) {
        this.id_sensor = id_sensor;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
