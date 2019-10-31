package com.example.appsensores.services;

import com.example.appsensores.models.Cuenta;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

import java.util.List;

public interface CuentasService {
    @GET("cuentas/listar_cuentas")
    Call<List<Cuenta>> getCuentas();

    @GET("cuentas/cuenta_por_id")
    Call<Cuenta> getCuenta(@Query("cuentaId") Long cuentaId);

    @PUT("cuentas/actualizar_cuenta")
    Call<Cuenta> updateCuenta(@Body Cuenta cuenta);

    @DELETE("cuentas/borrar_cuenta_por_id")
    Call<Cuenta> deleteCuenta(@Query("cuentaId") Long cuentaId);

    @POST("cuentas/insertar_cuenta")
    Call<Cuenta> insertCuenta(@Body Cuenta cuenta);
}
