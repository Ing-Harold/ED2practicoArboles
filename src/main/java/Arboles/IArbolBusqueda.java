/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arboles;
import excepciones.ExcepcionClaveNoExiste;
import java.util.List;

/**
 *
 * @author HAROLD
 * @param <K>
 * @param <V>

 */
public interface IArbolBusqueda<K extends Comparable <K>, V> {
    void insertar (K claveAInsertar, V valorAInsertar);
    V eliminar (K claveAEliminar) throws ExcepcionClaveNoExiste;
    V buscar (K claveABuscar);
    boolean contiene(K claveABuscar);
    int sixe();
    int altura();
    int nivel();
    void vaciar();
    boolean esArbolVacio();
    List<K> recorridoPorNiveles();
    List<K> recorridoEnPreOrden();
    List<K> recorridoEnInOrden();
    List<K> recorridoEnPostOrden();
    
}
