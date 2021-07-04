/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arboles;

import excepciones.ExcepcionClaveNoExiste;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author HAROLD
 * @param <K>
 * @param <V>
 */
public class ArbolBinarioBusqueda <K extends Comparable<K>, V> 
    implements IArbolBusqueda<K, V>{

    protected NodoBinario<K,V> raiz;

    public ArbolBinarioBusqueda() {
    }
    
    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if (claveAInsertar == null) {
            throw new RuntimeException("Clave no puede ser nula");
        }
        if (valorAInsertar == null) {
            throw new RuntimeException("Valor no puede ser nulo");
        }
        if (this.esArbolVacio()){
            this.raiz = new NodoBinario<>(claveAInsertar,valorAInsertar);
            return;
        }
        NodoBinario<K,V> nodoAnterior = NodoBinario.nodoVacio();
        NodoBinario<K,V> nodoActual = this.raiz;
        while (!NodoBinario.esNodoVacio(nodoActual)){
            K claveActual = nodoActual.getClave();
            nodoAnterior = nodoActual;
            if (claveAInsertar.compareTo(claveActual) < 0 ){
                //nodoAnterior = nodoActual;
                nodoActual = nodoActual.getHijoIzquierdo();
            }else if (claveAInsertar.compareTo(claveActual) > 0 ){
                //nodoAnterior = nodoActual;
                nodoActual = nodoActual.getHijoDerecho();
            }else {
                nodoActual.setValor(valorAInsertar);
                return;
            }
        }
        NodoBinario<K,V> nuevoNodo = new NodoBinario<>(claveAInsertar,valorAInsertar);
        K claveAnterior = nodoAnterior.getClave();
        if (claveAInsertar.compareTo(claveAnterior) < 0){
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
        }else {
            nodoAnterior.setHijoDerecho(nuevoNodo);
        }
    }

    @Override
    public V eliminar(K claveAEliminar) throws ExcepcionClaveNoExiste {
        V valorAEliminar = this.buscar(claveAEliminar);
        if (valorAEliminar == null){
            throw new ExcepcionClaveNoExiste();
        }
        this.raiz = eliminar(this.raiz,claveAEliminar);
        return valorAEliminar;
    }
    private NodoBinario<K,V> eliminar (NodoBinario<K,V> nodoActual, K claveAEliminar){
        K claveActual = nodoActual.getClave();
        if (claveAEliminar.compareTo(claveActual) < 0) {
            NodoBinario<K,V> supuestoNuevoHijoIzq = eliminar(nodoActual.getHijoIzquierdo(), claveAEliminar);
            nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzq);
            return nodoActual;
        }
        if (claveAEliminar.compareTo(claveActual) > 0 ){
            NodoBinario<K,V> supuestoNuevoHijoDer = eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
            nodoActual.setHijoDerecho(supuestoNuevoHijoDer);
            return nodoActual;
        }
        //caso 1 
        if (nodoActual.esHoja()){
            return NodoBinario.nodoVacio();
        }
        //caso 2.1 
        if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()){
            return nodoActual.getHijoIzquierdo();
        }
        //caso 2.1 
        if (nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()){
            return nodoActual.getHijoDerecho();
        }
        //caso 3
        NodoBinario<K,V> nodoDelSucesor = buscarSucesor(nodoActual.getHijoDerecho());
        NodoBinario<K,V> supuestoNuevoHijo = eliminar(nodoActual.getHijoDerecho(),nodoDelSucesor.getClave());
        nodoActual.setHijoDerecho(supuestoNuevoHijo);
        nodoActual.setClave(nodoActual.getClave());
        nodoActual.setValor(nodoActual.getValor());
        return nodoActual;
    }    
    private NodoBinario<K,V> buscarSucesor(NodoBinario<K,V> nodoActual){
        NodoBinario<K,V> nodoAnterior = NodoBinario.nodoVacio();
        do {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijoIzquierdo();
        }while (NodoBinario.esNodoVacio(nodoActual));
        return nodoAnterior;
    }
    @Override
    public V buscar(K claveABuscar) {
        if (this.esArbolVacio())
            return null;
        NodoBinario<K,V> nodoActual = this.raiz;
        while (!NodoBinario.esNodoVacio(nodoActual)){
            K claveActual = nodoActual.getClave();
            if (claveABuscar.compareTo(claveActual) < 0 ){
                nodoActual = nodoActual.getHijoIzquierdo();
            }else if (claveABuscar.compareTo(claveActual) > 0 ){
                nodoActual = nodoActual.getHijoDerecho();
            }else {
                return nodoActual.getValor();
            }    
        }
        //si llega aca la clava a buscar no esta en el arbol
        return null;
    }

    @Override
    public boolean contiene(K claveABuscar) {
        return this.buscar(claveABuscar)!= null;
    }

    @Override
    public int sixe() {
        if (this.esArbolVacio()){
            return 0;
        }
        int cantidad = 0;
        Queue<NodoBinario<K,V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoActual = colaDeNodos.poll();
            cantidad++;
            if (!nodoActual.esVacioHijoIzquierdo()){
                colaDeNodos.add(nodoActual.getHijoIzquierdo());
            }
            if (!nodoActual.esVacioHijoDerecho()){
                colaDeNodos.add(nodoActual.getHijoDerecho());
            }
        }
        return cantidad;
    }
    //recursivo
    public int sixeRec() {
        return sixeRec(this.raiz);
        
    }
    private int sixeRec(NodoBinario<K,V> nodoActual){
        if (NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantidadPorIzquierda = sixeRec(nodoActual.getHijoIzquierdo());
        int cantidadPorDerecha = sixeRec(nodoActual.getHijoDerecho());
        return cantidadPorDerecha + cantidadPorIzquierda + 1;
    }
    
    @Override
    public int altura() {
        if (this.esArbolVacio()){
            return 0;
        }
        int alturaDelArbol = 0;
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            int nroDeNodosDelNivel = colaDeNodos.size();
            NodoBinario<K, V> nodoActual = colaDeNodos.poll();
            int i = 0;
            while (i < nroDeNodosDelNivel) {
                i++;
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaDeNodos.add(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.add(nodoActual.getHijoDerecho());
                }
            }
            alturaDelArbol++;
        }
        return alturaDelArbol;
    }

    public int alturaRec() {
        return alturaRec(this.raiz);
        
    }
    
    protected int alturaRec(NodoBinario<K,V> nodoActual){
        if (NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int alturaPorIzquierda = alturaRec(nodoActual.getHijoIzquierdo());
        int alturaPorDerecha = alturaRec(nodoActual.getHijoDerecho());
        return alturaPorIzquierda > alturaPorDerecha? alturaPorIzquierda + 1 :alturaPorDerecha + 1;
    }
    
    @Override
    public int nivel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void vaciar() {
        this.raiz = NodoBinario.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(this.raiz);
    }

    @Override  //USANDO COLA 
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new LinkedList<>();
        if (this.esArbolVacio()){
            return recorrido; 
        }
        Queue<NodoBinario<K,V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoActual = colaDeNodos.poll();
            recorrido.add(nodoActual.getClave());
            if (!nodoActual.esVacioHijoIzquierdo()){
                colaDeNodos.add(nodoActual.getHijoIzquierdo());
            }
            if (!nodoActual.esVacioHijoDerecho()){
                colaDeNodos.add(nodoActual.getHijoDerecho());
            }
        }
        return recorrido;
    }

    @Override //USANDO UNA PILA  PRIMERO DE DERECHA A IZQUIERDA
    public List<K> recorridoEnPreOrden() {
        List<K> recorrido = new LinkedList<>();
        if (this.esArbolVacio())
            return recorrido; 
        Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<>();
        pilaDeNodos.push(this.raiz);
        while (!pilaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            if (!nodoActual.esVacioHijoDerecho()){
                pilaDeNodos.push(nodoActual.getHijoDerecho());
            }
            if (!nodoActual.esVacioHijoIzquierdo()){
                pilaDeNodos.push(nodoActual.getHijoIzquierdo());
            }
        }
        return recorrido;
        
    }
    
    //recursivo 
    public List<K> recorridoEnPreOrdenRe() {
        List<K> recorrido = new LinkedList<>();
        recorridoEnPreOrdenRe(this.raiz,recorrido);
        return recorrido;
    }
    private void recorridoEnPreOrdenRe(NodoBinario<K,V> nodoActual, List<K> recorrido){
        if (NodoBinario.esNodoVacio(nodoActual)){
            return ;
        }
        recorrido.add(nodoActual.getClave());
        recorridoEnPreOrdenRe(nodoActual.getHijoIzquierdo(), recorrido);
        recorridoEnPreOrdenRe(nodoActual.getHijoDerecho(), recorrido);
    }

    @Override //iterativo
    public List<K> recorridoEnInOrden() {
        List<K> recorrido = new LinkedList<>();
        if (this.esArbolVacio()){
            return recorrido;
        }
        Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<>();
        NodoBinario<K,V> nodoActual = this.raiz;
        insertarEnPilaParaInOrden(nodoActual, pilaDeNodos);
        while (!pilaDeNodos.isEmpty()){
            nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            insertarEnPilaParaInOrden(nodoActual.getHijoDerecho(), pilaDeNodos);
        }
        return recorrido;
    }

    private void insertarEnPilaParaInOrden(NodoBinario<K, V> nodoActual, Stack<NodoBinario<K, V>> pilaDeNodos) {
        while (!NodoBinario.esNodoVacio(nodoActual)){
            pilaDeNodos.push(nodoActual);
            if (!nodoActual.esVacioHijoIzquierdo()){
                nodoActual = nodoActual.getHijoIzquierdo();
            } else {
                return ;
            }
        }
    }
    
    //recursivo 
    public List<K> recorridoEnInOrdenRe() {
        List<K> recorrido = new LinkedList<>();
        recorridoEnInOrdenRe(this.raiz,recorrido);
        return recorrido;
    }
    private void recorridoEnInOrdenRe(NodoBinario<K,V> nodoActual, List<K> recorrido){
        if (NodoBinario.esNodoVacio(nodoActual)){
            return ;
        }
        recorridoEnInOrdenRe(nodoActual.getHijoIzquierdo(), recorrido);
        recorrido.add(nodoActual.getClave());
        recorridoEnInOrdenRe(nodoActual.getHijoDerecho(), recorrido);
    }
    
    @Override //iterativo 
    public List<K> recorridoEnPostOrden() {
        List<K> recorrido = new LinkedList<>();
        if (this.esArbolVacio())
            return recorrido; 
        Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<>();
        NodoBinario<K,V> nodoActual = this.raiz;
        insertarEnPilaParaPostOrden(nodoActual, pilaDeNodos);
        while (!pilaDeNodos.isEmpty()){
            nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            if (!pilaDeNodos.isEmpty()){
                NodoBinario<K,V> nodoDelTope = pilaDeNodos.peek();
                if (!nodoDelTope.esVacioHijoDerecho() &&
                        nodoDelTope.getHijoDerecho() != nodoActual){
                    insertarEnPilaParaPostOrden(nodoDelTope.getHijoDerecho(), pilaDeNodos);
                }
            } 
        }
        return recorrido;
    }

    private void insertarEnPilaParaPostOrden(NodoBinario<K, V> nodoActual, Stack<NodoBinario<K, V>> pilaDeNodos) {
        while (!NodoBinario.esNodoVacio(nodoActual)){
            pilaDeNodos.push(nodoActual);
            if (!nodoActual.esVacioHijoIzquierdo()){
                nodoActual = nodoActual.getHijoIzquierdo();
            }else {
                nodoActual = nodoActual.getHijoDerecho();
            }
        }
    }
    
    //recursivo 
    public List<K> recorridoEnPostOrdenRe() {
        List<K> recorrido = new LinkedList<>();
        recorridoEnPostOrdenRe(this.raiz,recorrido);
        return recorrido;
    }
    private void recorridoEnPostOrdenRe(NodoBinario<K,V> nodoActual, List<K> recorrido){
        if (NodoBinario.esNodoVacio(nodoActual)){
            return ;
        }
        recorridoEnPostOrdenRe(nodoActual.getHijoIzquierdo(), recorrido);
        recorridoEnPostOrdenRe(nodoActual.getHijoDerecho(), recorrido);
        recorrido.add(nodoActual.getClave());
    }
    
    
    public int cantidadHijosVacios() {
        if (NodoBinario.esNodoVacio(this.raiz)) {
            return 0;
        }
        int cantidad = 0;
        Queue<NodoBinario<K, V>> colaNodos = new LinkedList<>();
        colaNodos.offer(this.raiz);
        while (!colaNodos.isEmpty()) {
                NodoBinario<K, V> nodoActual = colaNodos.poll();
                if (nodoActual.esVacioHijoDerecho()) {
                    cantidad++;
                } 
                if (nodoActual.esVacioHijoIzquierdo()){
                    cantidad++;
                }
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    colaNodos.offer(nodoActual.getHijoDerecho());
                }            
        }
    return cantidad ;
    } 

    public boolean hijoVacioAntesDeNivel(int nivel) {
        if (NodoBinario.esNodoVacio(this.raiz)) {
            return false;
        }
        Queue<NodoBinario<K, V>> colaNodos = new LinkedList<>();
        colaNodos.offer(this.raiz);
        while (!colaNodos.isEmpty() && nivel > 0) {
            int cantidadNodosEnLaCola = colaNodos.size();
            int i = 0;
            while (i < cantidadNodosEnLaCola) {
                NodoBinario<K, V> nodoActual = colaNodos.poll();
                if (nodoActual.esVacioHijoIzquierdo() || nodoActual.esVacioHijoDerecho()) {
                    return true;
                }
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    colaNodos.offer(nodoActual.getHijoDerecho());
                }
                i++;
            }
            nivel--;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.generarCadenaDeArbol(this.raiz, "", true);
    }
    
    private String generarCadenaDeArbol(NodoBinario<K,V> nodoActual,
                        String prefijo, boolean ponerCodo) {
        StringBuilder cadena = new StringBuilder();
        cadena.append(prefijo);
        
        if (prefijo.length() == 0) {
            cadena.append(ponerCodo ? "────(RAIZ) " : "  ├────(R)"); //arbol vacio o no
        } else {
            cadena.append(ponerCodo ? "  └────(der)" : "  ├────(izq)");  //derecha o izquierda
        }
        if (NodoBinario.esNodoVacio(nodoActual)) {
            cadena.append("───╣\n");
            return cadena.toString();
        }
        cadena.append(nodoActual.getClave().toString());
        cadena.append("\n");

        NodoBinario<K,V> nodoIzq = nodoActual.getHijoIzquierdo();
        String prefijoAux = prefijo + (ponerCodo ? "           ":"   |    ");
        cadena.append(generarCadenaDeArbol(nodoIzq, prefijoAux, false));

        NodoBinario<K,V> nodoDer = nodoActual.getHijoDerecho();
        cadena.append(generarCadenaDeArbol(nodoDer, prefijoAux, true));

        return cadena.toString();
    }
    //implementar un metodo que calcule cuantas hojas hay en un arbol
    public int cantidadHojasRec(){
        return this.cantidadHojasRec(this.raiz);
    }
    private int cantidadHojasRec(NodoBinario<K,V> nodoActual){
        if (NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantidad = 0;
        if (nodoActual.esHoja()){
            return 1;
        }
        cantidad += this.cantidadHojasRec(nodoActual.getHijoDerecho());
        cantidad += this.cantidadHojasRec(nodoActual.getHijoIzquierdo());
        return cantidad;
    }
    //implementar un metodo que calcule cuantas hojas hay el nivel n del arbol
    public boolean vaciosAntesDeNivel (int nivel){
        if (this.esArbolVacio()){
            
        }
        return vaciosAntesDeNivel(this.raiz, nivel, 0);
    }
    private boolean vaciosAntesDeNivel  (NodoBinario<K,V> nodoActual, int nivelObjetivo, int nivelActual){
        if (NodoBinario.esNodoVacio(nodoActual)){
            return false;
        }
        if (nivelActual >= nivelObjetivo){
            return false;
        }
        if (nodoActual.esVacioHijoIzquierdo() || nodoActual.esVacioHijoDerecho()){
            return true;
        }
        boolean hayVaciosPorIzquierda = vaciosAntesDeNivel(nodoActual.getHijoIzquierdo(), 
                nivelObjetivo, nivelActual + 1);
        boolean hayVaciosPorDerecha = vaciosAntesDeNivel(nodoActual.getHijoDerecho(), 
                nivelObjetivo, nivelActual + 1);
        return hayVaciosPorDerecha || hayVaciosPorIzquierda;
    }
    //2. Implemente un método recursivo que retorne el nivel en que se encuentra una clave que se \n" +
//"recibe como parámetro. Devolver -1 si la clave no está en el árbol
    public int pregunta2(K claveABuscar){
        if (this.contiene(claveABuscar)){
            return pregunta2(this.raiz,claveABuscar);
        }
        return -1; 
    }
    private int pregunta2(NodoBinario<K,V> nodoActual,K claveABuscar){
        int nivel = -1;
        K claveActual = nodoActual.getClave();
        
        if(claveABuscar.compareTo(claveActual) < 0){
            nivel = this.pregunta2(nodoActual.getHijoIzquierdo(), claveABuscar) + 1;
        }
        if(claveABuscar.compareTo(claveActual) > 0){
            nivel = this.pregunta2(nodoActual.getHijoDerecho(), claveABuscar)+1;
        }
        if (claveABuscar.compareTo(claveActual)==0){
            return 0;
        }
        
        return nivel ;
    }
    
    
    
    
}
