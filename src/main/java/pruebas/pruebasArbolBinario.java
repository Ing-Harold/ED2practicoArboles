/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import Arboles.ArbolBinarioBusqueda;
import Arboles.IArbolBusqueda;
import excepciones.ExcepcionClaveNoExiste;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author HAROLD
 */
public class pruebasArbolBinario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExcepcionClaveNoExiste {
        System.out.println ("HELLO WORD");
        IArbolBusqueda<String,Integer> arbolEst = new ArbolBinarioBusqueda<>();
        arbolEst.insertar("Romero Harold", 100);
        arbolEst.insertar("Alva Jordi", 100);
        arbolEst.insertar("Kun Aguero", 100);
        arbolEst.insertar("Zinedin Zidan", 100);
        arbolEst.insertar("Bbbbbbb", 100);
        arbolEst.insertar("Aaaa", 100);
        arbolEst.insertar("Oooo", 100);
        ArbolBinarioBusqueda<String,Integer> arbolB = (ArbolBinarioBusqueda<String,Integer>) arbolEst;
        System.out.println(arbolEst.toString());
        System.out.println(arbolEst.sixe());
        System.out.println(arbolEst.eliminar("Romero Harold"));
        System.out.println("Recorrido por Nieveles" + arbolEst.recorridoPorNiveles());
        System.out.println("Recorrido en PreOrden" + arbolEst.recorridoEnPreOrden());
        System.out.println("Recorrido en PreOrden RE" + arbolB.recorridoEnPreOrdenRe());
        System.out.println("Recorrido en InOrden" + arbolEst.recorridoEnInOrden());
        System.out.println("Recorrido en InOrden RE" + arbolB.recorridoEnInOrdenRe());
        System.out.println("Recorrido en PostOrden" + arbolEst.recorridoEnPostOrden());
        System.out.println("Recorrido en PostOrden RE" + arbolB.recorridoEnPostOrdenRe());
    }
    
}
