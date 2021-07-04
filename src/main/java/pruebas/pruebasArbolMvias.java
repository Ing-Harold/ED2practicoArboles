/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import Arboles.ArbolMViasBusqueda;
import excepciones.ExcepcionClaveNoExiste;
import excepciones.ExcepcionOrdenInvalido;

        
/**
 *
 * @author HAROLD
 */
public class pruebasArbolMvias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExcepcionOrdenInvalido, ExcepcionClaveNoExiste {
        ArbolMViasBusqueda<Integer,Integer > arbolMvias;
        arbolMvias = new ArbolMViasBusqueda<>(4);
        arbolMvias.insertar(80, 10);  arbolMvias.insertar(120, 10);  arbolMvias.insertar(200, 10);
        arbolMvias.insertar(50, 10);  arbolMvias.insertar(70, 10);   arbolMvias.insertar(75, 10);
        arbolMvias.insertar(72, 10);  
        arbolMvias.insertar(98, 10);   arbolMvias.insertar(110, 10);
        arbolMvias.insertar(130, 10);  arbolMvias.insertar(140, 10); arbolMvias.insertar(150, 10);
        arbolMvias.insertar(134, 10);
        arbolMvias.insertar(160, 10);  arbolMvias.insertar(170, 10); arbolMvias.insertar(190, 10);
        arbolMvias.insertar(158, 10);
        arbolMvias.insertar(400, 10);  arbolMvias.insertar(500, 10); arbolMvias.insertar(560, 10);
        System.out.println("Recorrido en Por Niveles " + arbolMvias.recorridoPorNiveles());
        System.out.println("Recorrido en Pre Orden " + arbolMvias.recorridoEnPreOrden());
        System.out.println("Recorrido en In Orden " + arbolMvias.recorridoEnInOrden());
        System.out.println("Recorrido en Post Orden " + arbolMvias.recorridoEnPostOrden());
        System.out.println("Sixe Rec " + arbolMvias.recursivoSixeInOrden());
        System.out.println("Sixe " + arbolMvias.sixe());
        System.out.println("Nivel " + arbolMvias.nivel());
        System.out.println("cantidad Nodos Hijos Rec " + arbolMvias.cantidadNodoHojas());
        System.out.println("Clave Menor " + arbolMvias.menorClave());
        System.out.println("Clave Mayor " + arbolMvias.mayorClave());
        System.out.println(arbolMvias.eliminar(120));
        System.out.println(arbolMvias.eliminar(158));
        System.out.println("Recorrido en Por Niveles " + arbolMvias.recorridoPorNiveles());
        System.out.println("Recorrido en Pre Orden " + arbolMvias.recorridoEnPreOrden());
        System.out.println("Recorrido en In Orden " + arbolMvias.recorridoEnInOrden());
        System.out.println("Recorrido en Post Orden " + arbolMvias.recorridoEnPostOrden());
        System.out.println("Sixe " + arbolMvias.sixe());
        System.out.println("Nivel " + arbolMvias.nivel());
        System.out.println("cantidad Nodos Hijos Rec " + arbolMvias.cantidadNodoHojas());
    }
    
}
