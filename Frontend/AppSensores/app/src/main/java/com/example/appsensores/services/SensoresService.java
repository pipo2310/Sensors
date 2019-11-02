package com.example.appsensores.services;


import com.example.appsensores.models.Sensor;

import java.util.List;

import kotlin.Unit;
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

public interface SensoresService {
    @GET("sensores")
    Call<List<Sensor>> getSensores();

    @GET("sensores/sensor_por_id")
    Call<Sensor> getSensor(@Query("sensorId") Long sensorId);

    @FormUrlEncoded
    @POST("sensores")
    Call<Sensor> saveSensores(@Field("nombre") String nombre,
                              @Field("tipo") int tipo,
                              @Field("unidad") String unidad,
                              @Field("id_cuenta") Long id_cuenta,
                              @Field("alerta_amarilla") double alerta_amarilla,
                              @Field("alerta_roja") double alerta_roja
                            );


    @POST("sensores")
    Call<Sensor> crearSensor(@Body Sensor nuevoSensor);


    @FormUrlEncoded
    @PUT("sensores/{sensoresPk}")
    Call<Sensor> actualizarSensores(@Body Sensor nuevoSensor,
                                    @Path("sensoresPk") Long sensoresPk,
                                      @Field("nombre") String nombre,
                                      @Field("tipo") int tipo,
                                      @Field("unidad") String unidad,
                                      @Field("id_cuenta") Long id_cuenta,
                                      @Field("alerta_amarilla") double alerta_amarilla,
                                      @Field("alerta_roja") double alerta_roja
    );


    @PUT("sensores/{sensoresPk}")
    Call<Sensor> updateSensores( @Body Sensor nuevoSensor,
            @Path("sensoresPk") Long sensoresPk

    );

    @DELETE("sensores/{sensoresPk}")
    Call<Integer> borrarSensores(@Path("sensoresPk") Long sensoresPk
    );



}
