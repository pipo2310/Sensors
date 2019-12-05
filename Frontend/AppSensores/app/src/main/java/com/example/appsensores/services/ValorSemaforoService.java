package com.example.appsensores.services;

import com.example.appsensores.models.ValorSemaforo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ValorSemaforoService {
    @GET("valorSemaforo")
    Call<List<ValorSemaforo>> getValorSemaforo(@Query("tipo") int tipo);
}
