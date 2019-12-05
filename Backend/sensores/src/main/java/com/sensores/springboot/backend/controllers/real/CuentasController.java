package com.sensores.springboot.backend.controllers.real;

import com.sensores.springboot.backend.model.entity.real.Cuentas;
import com.sensores.springboot.backend.services.real.ICuentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentasController {

    @Autowired
    ICuentasService iCuentasService;

    @GetMapping("/listar_cuentas")
    public List<Cuentas> index(){
        return this.iCuentasService.findAll();
    }

    /**
     * Metodo para insertar una nueva Cuenta en la base de datos
     * @param cuentas cuenta que se desea insertar
     * @return Retorna la nueva cuenta insertada
     */
    @PostMapping("/insertar_cuenta")
    public Cuentas insertarCuenta(@RequestBody Cuentas cuentas){
        cuentas.setEsAdmin(false);
        try{
            // Se inserta y retorna la cuenta nueva
            cuentas.setLimiteAguaMedio(100L);
            cuentas.setLimiteAguaAlto(200L);
            cuentas.setLimiteGasMedio(100L);
            cuentas.setLimiteGasAlto(200L);
            cuentas.setLimiteElectMedio(100L);
            cuentas.setLimiteElectAlto(200L);
            return iCuentasService.save(cuentas);
        }catch (Exception ex){
            // Falta error
            return null;
        }

    }

    /**
     * Metodo para insertar una nueva Cuenta en la base de datos
     * @param cuentas cuenta que se desea insertar
     * @return Retorna la nueva cuenta insertada
     */
    @PutMapping("/actualizar_cuenta")
    public Cuentas actualizarCuenta(@RequestBody Cuentas cuentas){
        // Obtenemos la cuenta de la base de datos
        Cuentas cuenta = iCuentasService.findById(cuentas.getCuentasPk());
        // Se verifica si la cuenta existe
        if (cuenta != null){
            try{
                // Se inserta y retorna la cuenta nueva
                return iCuentasService.save(cuentas);
            }catch (Exception ex){
                // Falta error
                return null;
            }
        }else {
            // Falta error
            return null;
        }
    }

    /**
     * Metodo para eliminar una cuenta de la base de datos
     * @param cuentaId id de la cuenta que se quiere borrar
     * @return Retorna la cuenta eliminada
     */
    @DeleteMapping("borrar_cuenta_por_id")
    public Cuentas borrarCuentaPorId(@RequestParam long cuentaId){
        // Obtenemos la cuenta de la base de datos
        Cuentas cuenta = iCuentasService.findById(cuentaId);
        // Se verifica si la cuenta existe
        if (cuenta != null){
            // retornamos la cuenta eliminada
            try{
                iCuentasService.deleteById(cuentaId);
                return cuenta;
            }catch (Exception ex){
                // Falta error
                return null;
            }
        }else {
            // Falta error
            return null;
        }
    }

    /**
     * Metodo para buscar una cuenta de la base de datos
     * @param cuentaId id de la cuenta que se quiere buscar
     * @return Retorna la cuenta que se encontró
     */
    @GetMapping("cuenta_por_id")
    public Cuentas buscarCuentaPorId(@RequestParam long cuentaId){
        try{
            return iCuentasService.findById(cuentaId);
        }catch (Exception ex){
            // Falta error
            return null;
        }
    }

    @GetMapping("cuenta_por_correo_codigo")
    public Cuentas buscarCuentaPorCorreoOCodigo(@RequestParam String identificador){
        try{
            return iCuentasService.getCuentaByCorreoOrCodigo(identificador);
        }catch (Exception ex){
            // Falta error
            return null;
        }
    }
}
