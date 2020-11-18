/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alodiga.autorization.credential.response;

import com.alodiga.autorization.credential.response.Movimiento;
import java.util.ArrayList;

/**
 *
 * @author ltoro
 */
public class Movimientos {
    
    private ArrayList<Movimiento> movimiento;

    public Movimientos() {
    }

    public Movimientos(ArrayList<Movimiento> movimiento) {
        this.movimiento = movimiento;
    }

    public ArrayList<Movimiento> getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(ArrayList<Movimiento> movimiento) {
        this.movimiento = movimiento;
    }
    
    
    
    
}
