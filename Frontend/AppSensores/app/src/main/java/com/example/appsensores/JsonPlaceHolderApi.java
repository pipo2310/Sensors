package com.example.appsensores;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    @GET("sensores")
    Call<List<Sensores>> getSensores();

    @POST("sensores")
    @FormUrlEncoded
    Call<Sensores> saveSensores(@Field("nombre") String nombre,
                                @Field("unidad") String unidad,
                                @Field("id_cuenta") Long id_cuenta,
                                @Field("alerta_amarilla") float alerta_amarilla,
                                @Field("alerta_roja") float alerta_roja,
                                @Field("tipo") int tipo
                                );

}
