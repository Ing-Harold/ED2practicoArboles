/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arboles;

import excepciones.ExcepcionClaveNoExiste;
import excepciones.ExcepcionOrdenInvalido;
import java.util.Stack;

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
        super(orden);
        this.nroMaximoDeDatos = super.orden-1;
        this.nroMinimoDeDatos = this.nroMaximoDeDatos / 2;
        this.nroMinimoDeHijos = this.nroMinimoDeHijos + 1;
    }
    public void insertar (K claveAInsertar, V valorAInsertar){
        if(claveAInsertar == null ){
            throw new RuntimeException("No se permite insertar claves nulas");
        }
        if(valorAInsertar == null ){
            throw new RuntimeException("No se permite insertar valoress nulas");
        }
        if (this.esArbolVacio()){
            this.raiz = new NodoMVias<K,V>(this.orden+1,claveAInsertar,valorAInsertar);
            return;
        }
        Stack<NodoMVias<K,V>> pilaDeAncestro = new Stack<>();
        NodoMVias<K,V> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual)){
            int posicionDeClave = super.obtenerPosicionClaveEnNodo(nodoActual, claveAInsertar);
            if(posicionDeClave != super.POSICION_INVALIDA){
                nodoActual.setValor(posicionDeClave, valorAInsertar);
                nodoActual = NodoMVias.nodoVacio();
            } else {
                if(nodoActual.esHoja()){
                    this.insertarClaveYValorOrdenadoEnNodo(nodoActual, claveAInsertar, valorAInsertar);
                    if(nodoActual.cantidadDeClavesNoVacios() > this.nroMaximoDeDatos){
                        this.dividir(nodoActual, pilaDeAncestro);
                    }
                    nodoActual = NodoMVias.nodoVacio();
                } else {
                    int posicionPorDondeBajar = super.obtenerPosicionPorDondeBajar(nodoActual, claveAInsertar);
                    pilaDeAncestro.push(nodoActual);
                    nodoActual = nodoActual.getHijo(posicionPorDondeBajar);
                }
            }
        }
    }
    private void dividir(NodoMVias<K, V> nodoActual, Stack<NodoMVias<K, V>> pilaDeAncestros) {
        int pivote = nroMaximoDeDatos / 2;
        if (pilaDeAncestros.isEmpty()) {
            NodoMVias<K, V> padre = new NodoMVias<>(orden + 1, nodoActual.getClave(pivote), nodoActual.getValor(pivote));
            NodoMVias<K, V> hijoDer = new NodoMVias<>(orden + 1);
            int j = 0;
            for (int i = pivote + 1; i < orden; i++) {
                hijoDer.setClave(j, nodoActual.getClave(i));
                hijoDer.setValor(j, nodoActual.getValor(i));
                hijoDer.setHijo(j, nodoActual.getHijo(i));
                nodoActual.setClave(i, (K)NodoMVias.datoVacio());
                nodoActual.setValor(i, (V)NodoMVias.datoVacio());
                nodoActual.setHijo(i, null);
                j++;
            }
            hijoDer.setHijo(j, nodoActual.getHijo(orden));
            this.raiz = padre;
            nodoActual.setClave(pivote, (K)NodoMVias.datoVacio());
            nodoActual.setValor(pivote, (V)NodoMVias.datoVacio());
            this.raiz.setHijo(0, nodoActual);
            this.raiz.setHijo(raiz.cantidadDeClavesNoVacios(), hijoDer);

        } else {

            NodoMVias<K, V> nodoAncestro = pilaDeAncestros.pop();
            NodoMVias<K, V> hijoDer = new NodoMVias<>(orden + 1);
            //pasamos los valores derechos del pivote  al nuevo nodo
            int j = 0;
            for (int i = pivote + 1; i < orden; i++) {
                hijoDer.setClave(j, nodoActual.getClave(i));
                hijoDer.setValor(j, nodoActual.getValor(i));
                hijoDer.setHijo(j, nodoActual.getHijo(i));
                nodoActual.setClave(i, (K)NodoMVias.datoVacio());
                nodoActual.setValor(i, (V)NodoMVias.datoVacio());
                nodoActual.setHijo(i, null);
                j++;
            }
            hijoDer.setHijo(j, nodoActual.getHijo(orden));
            nodoActual.setHijo(orden, null);
            //nodos ya partidos
            super.insertarClaveYValorOrdenadoEnNodo(nodoAncestro, nodoActual.getClave(pivote), nodoActual.getValor(pivote));
            K clavePivote = nodoActual.getClave(pivote);
            V valorPivote = nodoActual.getValor(pivote);
            int posicionDonSetear = super.obtenerPosicionPorDondeBajar(nodoAncestro, clavePivote);
            nodoAncestro.setHijo(posicionDonSetear - 1, nodoActual);
            nodoAncestro.setHijo(posicionDonSetear, hijoDer);
            //elimniar dato pivote por que ya esta en nodoAncestro
            nodoActual.setClave(pivote, (K)NodoMVias.datoVacio());
            nodoActual.setValor(pivote, (V)NodoMVias.datoVacio());
            if (nodoAncestro.cantidadDeClavesNoVacios() > this.nroMaximoDeDatos) {
                dividir(nodoAncestro, pilaDeAncestros);
            } else {
                return;
            }

        }

    }
    
    @Override
    public V eliminar (K claveAEliminar) throws ExcepcionClaveNoExiste{
        V valorAEliminar = super.buscar(claveAEliminar);
        if (valorAEliminar == null){
            throw new IllegalArgumentException("Clave a eliminar no puede ser nula");
        }
        Stack<NodoMVias<K,V>> pilaDeAncestros = new Stack<>();
        NodoMVias<K,V> nodoActual = this.buscarNodoDeLaClave(claveAEliminar, pilaDeAncestros);
        if (NodoMVias.esNodoVacio(nodoActual)){
            throw new ExcepcionClaveNoExiste();
        }
        int posicionDeClaveAEliminarEnElNodo = super.obtenerPosicionClaveEnNodo(nodoActual, claveAEliminar);
        V valorARetonar = nodoActual.getValor(posicionDeClaveAEliminarEnElNodo);
        if(nodoActual.esHoja()){
            super.eliminarClaveYValorDelNodo(nodoActual, posicionDeClaveAEliminarEnElNodo);
        }
        if (nodoActual.cantidadDeClavesNoVacios() < this.nroMinimoDeDatos){
            if (pilaDeAncestros.isEmpty()){
                if (nodoActual.cantidadDeClavesNoVacios() == 0 ){
                    super.vaciar();
                }
            } else {
                this.prestarOFusionar(nodoActual,pilaDeAncestros);
            }
        } else {
            pilaDeAncestros.push(nodoActual);
            NodoMVias<K,V> nodoDelPredecesor = this.buscarNodoDeClavePredecesora(pilaDeAncestros, 
                    (K)nodoActual.getHijo(posicionDeClaveAEliminarEnElNodo));
            int posicionDelPredecesor = nodoDelPredecesor.cantidadDeClavesNoVacios() -1;
            K clavePredecesora = nodoDelPredecesor.getClave(posicionDelPredecesor);
            V valorPredecesora = nodoDelPredecesor.getValor(posicionDelPredecesor);
            super.eliminarClaveYValorDelNodo(nodoDelPredecesor, posicionDelPredecesor);
            nodoActual.setClave(posicionDeClaveAEliminarEnElNodo, clavePredecesora);
            nodoActual.setValor(posicionDeClaveAEliminarEnElNodo, valorPredecesora);
            if (nodoDelPredecesor.cantidadDeClavesNoVacios() < this.nroMinimoDeDatos){
                this.prestarOFusionar(nodoDelPredecesor,pilaDeAncestros);
            }
            
        }
        return valorAEliminar;
    }
    
    private NodoMVias<K,V> buscarNodoDeLaClave (K claveABuscar, Stack<NodoMVias<K,V>> pilaDeAncestros){
        
        return NodoMVias.nodoVacio();
    }
    private void prestarOFusionar(NodoMVias<K,V> nodoActual, Stack<NodoMVias<K,V>>pilaDeAncestros){
        
    }
    protected NodoMVias<K,V> buscarNodoDeClavePredecesora(Stack<NodoMVias<K,V>> pila,K clave){
        return null;
    }
    //5. Implemente un método iterativo que retorne la cantidad de datos vacios y no vacíos en un \n" +
//"árbol b, pero solo antes del nivel N
    public int pregunta5datosVacios(int n){
        return pregunta5datosVacios(this.raiz,n);
    }
    private int pregunta5datosVacios(NodoMVias<K,V> nodoActual, int n){
        if(NodoMVias.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantidad = 0;
        if (n > 0){
            cantidad = nodoActual.cantidadDeClavesVacios()-1;
        }
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacios(); i++){
            cantidad += this.pregunta5datosVacios(nodoActual.getHijo(i),n - 1);
        }
        cantidad += this.pregunta5datosVacios(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacios()),n-1);
        return cantidad;
    }
    public int pregunta5datosNoVacios(int n){
        return pregunta5datosNoVacios(this.raiz,n);
    }
    private int pregunta5datosNoVacios(NodoMVias<K,V> nodoActual, int n){
        if(NodoMVias.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantidad = 0;
        if (n > 0){
            cantidad = nodoActual.cantidadDeClavesNoVacios();
        }
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacios(); i++){
            cantidad += this.pregunta5datosNoVacios(nodoActual.getHijo(i),n - 1);
        }
        cantidad += this.pregunta5datosNoVacios(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacios()),n-1);
        return cantidad;
    }
    //9. Para un árbol b implemente un método que retorne verdadero si todos sus nodos no hojas \n" +
//"no tienen datos vacíos, falso en caso contrario
    public boolean pregunta9(){
        return pregunta9(this.raiz);
    }
    private boolean pregunta9(NodoMVias<K,V> nodoActual){
        if (NodoMVias.esNodoVacio(nodoActual)){
            return false;
        }
        boolean b = false;
        if (!nodoActual.esHoja()){
            if (nodoActual.cantidadDeClavesNoVacios() == this.nroMaximoDeDatos){
                return true;
            }
        }
        for(int i = 0; i < this.orden; i++){
            b = this.pregunta9(nodoActual.getHijo(i));
        }
        return b ;
    }
    
}
