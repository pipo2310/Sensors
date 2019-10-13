package com.example.appsensores;


import java.util.List;

import kotlin.Unit;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {
    @GET("sensores")
    Call<List<Sensor>> getSensores();

    @FormUrlEncoded
    @POST("sensores")
    Call<Sensor> saveSensores(@Path("sensoresPk") Long sensoresPk,
                              @Field("nombre") String nombre,
                              @Field("tipo") int tipo,
                            @Field("unidad") String unidad,
                            @Field("id_cuenta") Long id_cuenta,
                            @Field("alerta_amarilla") double alerta_amarilla,
                            @Field("alerta_roja") double alerta_roja
                            );

    @FormUrlEncoded
    @PUT("sensores/{sensoresPk}")
    Call<Sensor> actualizarSensores(@Path("sensoresPk") Long sensoresPk,
                                      @Field("nombre") String nombre,
                                      @Field("tipo") int tipo,
                                      @Field("unidad") String unidad,
                                      @Field("id_cuenta") Long id_cuenta,
                                      @Field("alerta_amarilla") double alerta_amarilla,
                                      @Field("alerta_roja") double alerta_roja
    );

    @DELETE("sensores/{sensoresPk}")
    Call<Unit> borrarSensores(@Path("sensoresPk") Long sensoresPk
    );

}
