package com.example.appsensores.services;

import com.example.appsensores.models.Cuenta;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface CuentasService {
    @GET("cuentas")
    Call<List<Cuenta>> getCuentas();
}
