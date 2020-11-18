/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alodiga.autorization.credential.response;

import java.util.ArrayList;

/**
 *
 * @author ltoro
 */
public class Movimiento {
    
    private String fecha;
    private String hora;
    private String comercio;
    private String importe;
    private String descripcionImporte;
    private String codigoAutorizacion;

    public Movimiento() {
    }

    public Movimiento(String fecha, String hora, String comercio, String importe, String descripcionImporte, String codigoAutorizacion) {
        this.fecha = fecha;
        this.hora = hora;
        this.comercio = comercio;
        this.importe = importe;
        this.descripcionImporte = descripcionImporte;
        this.codigoAutorizacion = codigoAutorizacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getComercio() {
        return comercio;
    }

    public void setComercio(String comercio) {
        this.comercio = comercio;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getDescripcionImporte() {
        return descripcionImporte;
    }

    public void setDescripcionImporte(String descripcionImporte) {
        this.descripcionImporte = descripcionImporte;
    }

    public String getCodigoAutorizacion() {
        return codigoAutorizacion;
    }

    public void setCodigoAutorizacion(String codigoAutorizacion) {
        this.codigoAutorizacion = codigoAutorizacion;
    }
    
    
    
    
    
    
}
