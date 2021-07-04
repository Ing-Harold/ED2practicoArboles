/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arboles;

import excepciones.ExcepcionOrdenInvalido;

/**
 *
 * @author HAROLD
 */
public class ArbolB <K extends Comparable<K>,V> extends ArbolMViasBusqueda<K, V>{
    private int nroMaximoDeDatos;
    private int nroMinimoDeDatos;
    private int nroMinimoDeHijos;
    
    public ArbolB(){
        super();
        this.nroMaximoDeDatos = 2;
        this.nroMinimoDeDatos = 1;
        this.nroMinimoDeHijos = 2;
    }
    
    public ArbolB(int orden) throws ExcepcionOrdenInvalido{
        super();
        this.nroMaximoDeDatos = 
        this.nroMinimoDeDatos = super.orden-1;
    }
}
