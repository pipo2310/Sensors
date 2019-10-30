package com.example.appsensores.services;


import com.example.appsensores.models.Sensor;
import com.example.appsensores.models.TipoSensor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TiposSensorService {
    @GET("tipo_sensor")
    Call<List<TipoSensor>> getTiposSensor();

    @PUT("tipo_sensor/{id}")
    Call<TipoSensor> updateTipoSensor(@Path("id") Integer id, @Body TipoSensor tiposensor);


}
