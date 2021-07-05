/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arboles;

import excepciones.ExcepcionClaveNoExiste;
import excepciones.ExcepcionOrdenInvalido;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author HAROLD //1/2021
 */
public class ArbolMViasBusqueda <K extends Comparable<K>, V> implements
        IArbolBusqueda<K, V> {
    protected NodoMVias<K,V> raiz;
    protected int orden;
    protected int POSICION_INVALIDA = -1;   
    
    public ArbolMViasBusqueda(){
        this.orden = 3;
    }
    
    public ArbolMViasBusqueda(int orden) throws ExcepcionOrdenInvalido{
        if (orden < 3)
            throw new ExcepcionOrdenInvalido();
        this.orden = orden;
    }
    
    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if (claveAInsertar == null){
            throw new RuntimeException("No se permite claves nulos");
        }
        if (valorAInsertar == null){
            throw new RuntimeException("No se permite valores nulos");
        }
        if (this.esArbolVacio()){
            this.raiz = new NodoMVias<>(this.orden,claveAInsertar,valorAInsertar);
            return;
        }
        NodoMVias<K,V> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual)){
            int posicionDeClave = this.obtenerPosicionClaveEnNodo(nodoActual,claveAInsertar);
            if (posicionDeClave != POSICION_INVALIDA){
                nodoActual.setValor(posicionDeClave, valorAInsertar);
                nodoActual = NodoMVias.nodoVacio();
            } else {//insertar en un nodo hoja
                if (nodoActual.esHoja()){
                    if (nodoActual.estanClavesLenas()){
                        int posicionPorDondeBajar = this.obtenerPosicionPorDondeBajar(nodoActual, claveAInsertar);
                        NodoMVias<K,V> nuevoHijo = new NodoMVias<>(this.orden,claveAInsertar,valorAInsertar);
                        nodoActual.setHijo(posicionPorDondeBajar, nuevoHijo);
                    } else {
                        this.insertarClaveYValorOrdenadoEnNodo (nodoActual,claveAInsertar,valorAInsertar);
                    }
                    nodoActual = NodoMVias.nodoVacio();
                } else{ //si no es hoja
                    int posicionPorDondeBajar = this.obtenerPosicionPorDondeBajar(nodoActual, claveAInsertar);
                    if (nodoActual.esHijoVacio(posicionPorDondeBajar)){
                        NodoMVias<K,V> nuevoHijo = new NodoMVias<>(this.orden,claveAInsertar,valorAInsertar);
                        nodoActual.setHijo(posicionPorDondeBajar, nuevoHijo);
                        nodoActual = NodoMVias.nodoVacio();
                    } else { // si donde bajamos existe nodo actualizamos nodoActual
                        nodoActual = nodoActual.getHijo(posicionPorDondeBajar);
                    }                    
                }
            }
        }
    }
    //metodo por implementar
    protected int obtenerPosicionClaveEnNodo (NodoMVias<K,V> nodoActual, K claveABuscar){
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacios(); i++){
            K claveActual = nodoActual.getClave(i);
            if (claveABuscar.compareTo(claveActual) == 0){
                return i;
            }
        }
        return -1;
    }
    //metodo por implementar
    protected int obtenerPosicionPorDondeBajar (NodoMVias<K,V> nodoActual, K claveAInsertar){
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacios(); i++){
            K claveActual = nodoActual.getClave(i);
            if (claveAInsertar.compareTo(claveActual) < 0){
                return i;
            }
        }
        return nodoActual.cantidadDeClavesNoVacios();
    }
    //metodo por implementar
    protected void insertarClaveYValorOrdenadoEnNodo(NodoMVias<K,V> nodoActual, K claveAInsertar, V valorAInsertar){
        int posicionAInsertar = this.obtenerPosicionPorDondeBajar(nodoActual, claveAInsertar);
        for (int i = nodoActual.cantidadDeClavesNoVacios(); i > posicionAInsertar; i--){
            nodoActual.setClave(i, nodoActual.getClave(i-1));
            nodoActual.setValor(i, nodoActual.getValor(i-1));
            //a probar
            nodoActual.setHijo(i + 1 , nodoActual.getHijo(i));
        }
        nodoActual.setClave(posicionAInsertar, claveAInsertar);
        nodoActual.setValor(posicionAInsertar, valorAInsertar);
    }
    
    @Override
    public V eliminar(K claveAEliminar) throws ExcepcionClaveNoExiste {
        V valorAEliminar = this.buscar(claveAEliminar);
        if (valorAEliminar == null){
            throw new ExcepcionClaveNoExiste();
        }
        this.raiz = this.eliminar(this.raiz, claveAEliminar);
        return valorAEliminar;
    }

    private NodoMVias<K,V> eliminar(NodoMVias<K,V> nodoActual, K claveAEliminar){
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacios(); i++){
            K claveActual = nodoActual.getClave(i);
            if (claveAEliminar.compareTo(claveActual) == 0){
                if (nodoActual.esHoja()){ //si la clave se encuentra en un nodoHoja
                    this.eliminarClaveYValorDelNodo(nodoActual,i);
                    if(nodoActual.cantidadDeClavesNoVacios() == 0){
                        return NodoMVias.nodoVacio();
                    }
                    return nodoActual;
                }//si no es hoja 
                K claveDeReemplazo;
                if (this.hayHijosMasAdelante(nodoActual,i)){
                    claveDeReemplazo = this.buscarClaveSucesoraInOrden(nodoActual, claveAEliminar);
                } else {
                    claveDeReemplazo =this.buscarClavePredecesoraInOrden(nodoActual, claveAEliminar);
                }
                V valorDeReemplazo = this.buscar(claveDeReemplazo);
                nodoActual = eliminar(nodoActual,claveDeReemplazo);
                nodoActual.setClave(i, claveDeReemplazo);
                nodoActual.setValor(i, valorDeReemplazo);
                return nodoActual;
            }
            //si no esta en la posicion i bajamos
            if (claveAEliminar.compareTo(claveActual) < 0){
                NodoMVias<K,V> supuestoNuevoHijo = this.eliminar(nodoActual.getHijo(i),claveAEliminar);
                nodoActual.setHijo(i, supuestoNuevoHijo);
                return nodoActual;
            }
        }
        //bajar al ultimo hijo 
        NodoMVias<K,V> supuestoNuevoHijo = this.eliminar(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacios()), claveAEliminar);
        nodoActual.setHijo(nodoActual.cantidadDeClavesNoVacios(), supuestoNuevoHijo);
        return nodoActual;
    }
    protected void eliminarClaveYValorDelNodo(NodoMVias<K,V> nodoActual,int posicionAEliminar){
        for (int i = posicionAEliminar; i < nodoActual.cantidadDeClavesNoVacios()-1; i++){
            nodoActual.setClave(i, nodoActual.getClave(i+1));
            nodoActual.setValor(i, nodoActual.getValor(i+1));
        }
        int n = nodoActual.cantidadDeClavesNoVacios()-1;
        nodoActual.setClave(n, (K) NodoMVias.datoVacio());
        nodoActual.setValor(n, (V) NodoMVias.datoVacio());
    }
    //metodo pa casa 
    private boolean hayHijosMasAdelante(NodoMVias<K,V> nodoActual, int posicion){//dudas
        for(int i = posicion; i < nodoActual.cantidadDeClavesNoVacios(); i++){
            if (!nodoActual.esHijoVacio(i)){
                return true;
            }
        }
        if (!nodoActual.esHijoVacio(nodoActual.cantidadDeClavesNoVacios())){
            return true;
        }
        return false;
    }
    //metodo pa casa
    private K buscarClaveSucesoraInOrden(NodoMVias<K,V> nodoActual, K claveABuscar){
        K claveSucesora = (K)NodoMVias.datoVacio();
        int posicion = this.obtenerPosicionClaveEnNodo(nodoActual, claveABuscar);
        if (nodoActual.esHijoVacio(posicion + 1)){
            claveSucesora = nodoActual.getClave(posicion + 1);
            return claveSucesora;
        }else {
            nodoActual = nodoActual.getHijo(posicion + 1);
            while(!nodoActual.esHijoVacio(0)){
                nodoActual = nodoActual.getHijo(0);
            }
            claveSucesora = nodoActual.getClave(0);
        }
        return claveSucesora;
    }
    //metodo pa casa
    private K buscarClavePredecesoraInOrden(NodoMVias<K,V> nodoActual, K claveABuscar){
        K claveSucesora = (K)NodoMVias.datoVacio();
        int posicion = this.obtenerPosicionClaveEnNodo(nodoActual, claveABuscar);
        if (nodoActual.esHijoVacio(posicion)){
            claveSucesora = nodoActual.getClave(posicion - 1);
            return claveSucesora;
        }else {
            nodoActual = nodoActual.getHijo(posicion);
            while(!nodoActual.esHijoVacio(nodoActual.cantidadDeClavesNoVacios())){
                nodoActual = nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacios());
            }
            claveSucesora = nodoActual.getClave(nodoActual.cantidadDeClavesNoVacios());
        }
        return claveSucesora;
    }
    
    @Override
    public V buscar(K claveABuscar) {
        NodoMVias<K,V> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual)){
            boolean huboCambioNodoActual = false;
            for (int i = 0 ; i < nodoActual.cantidadDeClavesNoVacios() && 
                    !huboCambioNodoActual; i ++){
                K claveActual = nodoActual.getClave(i);
                if (claveActual.compareTo(claveABuscar) == 0){
                    return nodoActual.getValor(i);
                }
                if (claveABuscar.compareTo(claveActual) < 0){
                    nodoActual = nodoActual.getHijo(i);
                    huboCambioNodoActual = true;
                }
            }
            if (!huboCambioNodoActual){
                nodoActual = nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacios());
            }
        }
        return (V) NodoMVias.datoVacio();
    }

    @Override
    public boolean contiene(K claveABuscar) {
        return this.buscar(claveABuscar) != null;
    }

    @Override
    public int sixe() {
        if (this.esArbolVacio()){
            return 0;
        }
        Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        int cantidad = 0;
        while (!colaDeNodos.isEmpty()){
            NodoMVias<K,V> nodoActual = colaDeNodos.poll();
            cantidad++;
            for (int i = 0; i < nodoActual.cantidadDeClavesNoVacios(); i++){
                if (!nodoActual.esHijoVacio(i)){
                    colaDeNodos.offer(nodoActual.getHijo(i));
                }
            }
            if (!nodoActual.esHijoVacio(nodoActual.cantidadDeClavesNoVacios())){
                colaDeNodos.offer(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacios()));
            }
        }
        return cantidad;
    }
    
    @Override
    public int altura() {
        return altura(this.raiz);
    }

    private int altura (NodoMVias<K,V> nodoActual){
        if (NodoMVias.esNodoVacio(nodoActual)){
            return 0;
        }
        int alturaMayor = 0;
        for (int i = 0; i < orden; i++){
            int alturaActual = altura(nodoActual.getHijo(i));
            if (alturaActual > alturaMayor){
                alturaMayor = alturaActual;
            }
        }
        return alturaMayor +1; 
    }
    @Override
    public int nivel() {
        return nivel(this.raiz);
    }

    private int nivel(NodoMVias<K,V> nodoActual){
        if (NodoMVias.esNodoVacio(nodoActual)){
            return -1;
        }
        
        int nivelMayor = -1;
        for (int i = 0; i < this.orden; i++){
            int nivelActual = this.nivel(nodoActual.getHijo(i));
            if (nivelActual > nivelMayor){
                nivelMayor = nivelActual;
            }
        }
        
        return nivelMayor + 1;
    }
    @Override
    public void vaciar() {
        this.raiz = NodoMVias.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return NodoMVias.esNodoVacio(this.raiz);
    }

    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new LinkedList<>();
        if (this.esArbolVacio()){
            return recorrido;
        }
        Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()){
            NodoMVias<K,V> nodoActual = colaDeNodos.poll();
            for (int i = 0; i < nodoActual.cantidadDeClavesNoVacios(); i++){
                recorrido.add(nodoActual.getClave(i));
                if (!nodoActual.esHijoVacio(i)){
                    colaDeNodos.offer(nodoActual.getHijo(i));
                }
            }
            if (!nodoActual.esHijoVacio(nodoActual.cantidadDeClavesNoVacios())){
                colaDeNodos.offer(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacios()));
            }
        }
        return recorrido;
    }

    @Override //recursivo
    public List<K> recorridoEnPreOrden() {
        List<K> recorrido = new LinkedList<>();
        recorridoEnPreOrden(this.raiz,recorrido);
        return recorrido;              
    }

    private void recorridoEnPreOrden(NodoMVias<K,V> nodoActual, List<K> recorrido){
        if (NodoMVias.esNodoVacio(nodoActual)){
            return;
        }
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacios() ; i++){
            recorrido.add(nodoActual.getClave(i));
            recorridoEnPreOrden(nodoActual.getHijo(i), recorrido);
        }
        recorridoEnPreOrden(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacios()), recorrido);
    }
    
    @Override //tarea hacer recorridoEnInOrden 8/6/21
    public List<K> recorridoEnInOrden() {
        List<K> recorrido = new LinkedList<>();
        recorridoEnInOrden(this.raiz,recorrido);
        return recorrido;              
    }

    private void recorridoEnInOrden(NodoMVias<K,V> nodoActual, List<K> recorrido){
        if (NodoMVias.esNodoVacio(nodoActual)){
            return;
        }
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacios() ; i++){
            recorridoEnInOrden(nodoActual.getHijo(i), recorrido);
            recorrido.add(nodoActual.getClave(i));
        }
        recorridoEnInOrden(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacios()), recorrido);
    }

    @Override
    public List<K> recorridoEnPostOrden() {
       List<K> recorrido = new LinkedList<>();
        recorridoEnPostOrden(this.raiz,recorrido);
        return recorrido;              
    }

    private void recorridoEnPostOrden(NodoMVias<K,V> nodoActual, List<K> recorrido){
        if (NodoMVias.esNodoVacio(nodoActual)){
            return;
        }
        recorridoEnPostOrden(nodoActual.getHijo(0), recorrido);  //simular hijo derecho 
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacios() ; i++){
            recorridoEnPostOrden(nodoActual.getHijo(i+1), recorrido);
            recorrido.add(nodoActual.getClave(i));
        }
    }
    //practicas\
    //menorClave
    public K menorClave(){
        if (this.esArbolVacio()){
            return (K)NodoMVias.nodoVacio();
        }
        NodoMVias<K,V> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual.getHijo(0))){
            nodoActual = nodoActual.getHijo(0);
        }
        K claveMenor = nodoActual.getClave(0);
        return claveMenor;
    }
    //practicas mayorClave
    public K mayorClave(){
        if (this.esArbolVacio()){
            return (K)NodoMVias.nodoVacio();
        }
        NodoMVias<K,V> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacios()))){
            nodoActual = nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacios());
        }
        K claveMenor = nodoActual.getClave(nodoActual.cantidadDeClavesNoVacios()-1);
        return claveMenor;
    }
    //practicar
    //cuantos nodos son hojas en MVIAS
    public int cantidadNodoHojas(){
        return cantidadNodoHojas(this.raiz);
    }
    
    private int cantidadNodoHojas(NodoMVias<K,V> nodoActual){
        if (NodoMVias.esNodoVacio(nodoActual)){
            return 0;
        }
        if (nodoActual.esHoja()){
            return 1;
        }
        int cantidad = 0;
        for (int i = 0; i < this.orden; i++){
            cantidad+=this.cantidadNodoHojas(nodoActual.getHijo(i));
        }
        return cantidad;
    }
    //practicas 
    public int recursivoSixeInOrden(){
        return recursivoSixeInOrden(this.raiz);
    }

    private int recursivoSixeInOrden(NodoMVias<K,V> nodoActual){
        if (NodoMVias.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantidad = 0 ;
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacios(); i++){     
            cantidad++;
            cantidad+=this.recursivoSixeInOrden(nodoActual.getHijo(i));
        }
        cantidad+=this.recursivoSixeInOrden(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacios()));
        return cantidad;
    }
    //practicas 
    //retornar la cantidad de hojas en un arbol m vias
    //a partir del nivel n
    public int cantidadHojasDesdeNivel(int nivel){
        return cantidadHojasDesdeNivel(this.raiz,nivel,0);
    }
    
    private int cantidadHojasDesdeNivel(NodoMVias<K,V> nodoActual ,int nivel, int n){
        if (NodoMVias.esNodoVacio(nodoActual)){
            return 0;
        }
        if (n >= nivel){
            if (nodoActual.esHoja()){
                return 1;
            }
        }
        int cantidad = 0;
        for (int i = 0; i < orden; i++ ){
            cantidad+=this.cantidadHojasDesdeNivel(nodoActual.getHijo(i), nivel, n+1);
        }
        return cantidad;
    }
    //3. Implemente un método recursivo que retorne la cantidad de datos no vacíos que hay en el \n" +
//"nivel N de un árbol m-vias de búsqueda
    public int pregunta3(int nivel){
        return this.pregunta3(this.raiz,nivel);
    }
    private int pregunta3(NodoMVias<K,V> nodoActual ,int nivel){
        if (NodoMVias.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantidad = 0;
        if (nivel == 0){
            cantidad =  nodoActual.cantidadDeClavesNoVacios();
        } else {
            for (int i = 0; i < nodoActual.cantidadDeClavesNoVacios(); i++) {
                cantidad += this.pregunta3(nodoActual.getHijo(i), nivel - 1);
            }
            cantidad += this.pregunta3(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacios()), nivel - 1);
        }
        return cantidad;
    }
    //4. Implemente un método recursivo que retorne la cantidad de nodos hojas en un árbol m vías \n" +
//"de búsqueda, pero solo después del nivel N
    public int pregunta4(int nivel){
        return this.pregunta4(this.raiz, nivel);
    }
    private int pregunta4(NodoMVias<K,V> nodoActual, int nivel){
        if (NodoMVias.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantidad = 0;
        if ( nivel < 0 ){
            if (nodoActual.esHoja()){
                return 1;
            }
        }else {
            for (int i = 0; i < nodoActual.cantidadDeClavesNoVacios(); i ++){
                cantidad+=this.pregunta4(nodoActual.getHijo(i), nivel - 1);
            }
            cantidad+=this.pregunta4(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacios()), nivel - 1);
        }
        return cantidad;
    }
    //6. Implemente un método que retorne verdadero si solo hay hojas en el último nivel de un " +
//"árbol m-vias de búsqueda. Falso en caso contrario.
//    public boolean pregunta6(){
//        
//    }
}