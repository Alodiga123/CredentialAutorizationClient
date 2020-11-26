/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alodiga.autorization.credential.response;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ltoro
 */
public class BalanceInquiryWithoutMovementsResponse implements Serializable {

    private String codigoError;
    private String mensajeError;
    private String codigoRespuesta;
    private String mensajeRespuesta;
    private String codigoAutorizacion;
    private String DisponibleConsumos;
    private String DisponibleCuotas;
    private String DisponibleAdelantos;    
    private String DisponiblePrestamos;
    private String Saldo;
    private String SaldoEnDolares;
    private String PagoMinimo;
    private String FechaVencimientoUltimaLiquidacion;  
    

    public BalanceInquiryWithoutMovementsResponse() {
    }

    public BalanceInquiryWithoutMovementsResponse(String codigoError, String mensajeError, String codigoRespuesta, String mensajeRespuesta) {
        this.codigoError = codigoError;
        this.mensajeError = mensajeError;
        this.codigoRespuesta = codigoRespuesta;
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public BalanceInquiryWithoutMovementsResponse(String codigoError, String mensajeError, String codigoRespuesta, String mensajeRespuesta, String codigoAutorizacion, String DisponibleConsumos, String DisponibleCuotas, String DisponibleAdelantos, String DisponiblePrestamos, String Saldo, String SaldoEnDolares, String PagoMinimo) {
        this.codigoError = codigoError;
        this.mensajeError = mensajeError;
        this.codigoRespuesta = codigoRespuesta;
        this.mensajeRespuesta = mensajeRespuesta;
        this.codigoAutorizacion = codigoAutorizacion;
        this.DisponibleConsumos = DisponibleConsumos;
        this.DisponibleCuotas = DisponibleCuotas;
        this.DisponibleAdelantos = DisponibleAdelantos;
        this.DisponiblePrestamos = DisponiblePrestamos;
        this.Saldo = Saldo;
        this.SaldoEnDolares = SaldoEnDolares;
        this.PagoMinimo = PagoMinimo;
        
    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public String getCodigoAutorizacion() {
        return codigoAutorizacion;
    }

    public void setCodigoAutorizacion(String codigoAutorizacion) {
        this.codigoAutorizacion = codigoAutorizacion;
    }

    public String getDisponibleConsumos() {
        return DisponibleConsumos;
    }

    public void setDisponibleConsumos(String DisponibleConsumos) {
        this.DisponibleConsumos = DisponibleConsumos;
    }

    public String getDisponibleCuotas() {
        return DisponibleCuotas;
    }

    public void setDisponibleCuotas(String DisponibleCuotas) {
        this.DisponibleCuotas = DisponibleCuotas;
    }

    public String getDisponibleAdelantos() {
        return DisponibleAdelantos;
    }

    public void setDisponibleAdelantos(String DisponibleAdelantos) {
        this.DisponibleAdelantos = DisponibleAdelantos;
    }

    public String getDisponiblePrestamos() {
        return DisponiblePrestamos;
    }

    public void setDisponiblePrestamos(String DisponiblePrestamos) {
        this.DisponiblePrestamos = DisponiblePrestamos;
    }

    public String getSaldo() {
        return Saldo;
    }

    public void setSaldo(String Saldo) {
        this.Saldo = Saldo;
    }

    public String getSaldoEnDolares() {
        return SaldoEnDolares;
    }

    public void setSaldoEnDolares(String SaldoEnDolares) {
        this.SaldoEnDolares = SaldoEnDolares;
    }

    public String getPagoMinimo() {
        return PagoMinimo;
    }

    public void setPagoMinimo(String PagoMinimo) {
        this.PagoMinimo = PagoMinimo;
    }

    public String getFechaVencimientoUltimaLiquidacion() {
        return FechaVencimientoUltimaLiquidacion;
    }

    public void setFechaVencimientoUltimaLiquidacion(String FechaVencimientoUltimaLiquidacion) {
        this.FechaVencimientoUltimaLiquidacion = FechaVencimientoUltimaLiquidacion;
    }

    @Override
    public String toString() {
        return "BalanceInquiryWithoutMovementsResponse{" + "codigoError=" + codigoError + ", mensajeError=" + mensajeError + ", codigoRespuesta=" + codigoRespuesta + ", mensajeRespuesta=" + mensajeRespuesta + ", codigoAutorizacion=" + codigoAutorizacion + ", DisponibleConsumos=" + DisponibleConsumos + ", DisponibleCuotas=" + DisponibleCuotas + ", DisponibleAdelantos=" + DisponibleAdelantos + ", DisponiblePrestamos=" + DisponiblePrestamos + ", Saldo=" + Saldo + ", SaldoEnDolares=" + SaldoEnDolares + ", PagoMinimo=" + PagoMinimo + ", FechaVencimientoUltimaLiquidacion=" + FechaVencimientoUltimaLiquidacion + '}';
    }

    
 
   
}
