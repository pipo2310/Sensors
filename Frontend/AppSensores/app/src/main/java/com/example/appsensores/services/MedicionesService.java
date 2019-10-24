package com.example.appsensores.services;

import com.example.appsensores.models.Medicion;

import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MedicionesService {

    @GET("sensoresLogs_mes")
    Call<List<Medicion>> getMedicionesAno(@Query("tipo") int tipo);

    @GET("sensoresLogs_semana")
    Call<Collection<Medicion>> getMedicionesMes(@Query("tipo") int tipo);

    @GET("sensoresLogs_dia")
    Call<Collection<Medicion>> getMedicionesSemana(@Query("tipo") int tipo);
}
