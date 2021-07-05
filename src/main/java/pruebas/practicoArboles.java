/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import Arboles.AVL;
import Arboles.ArbolB;
import Arboles.ArbolBinarioBusqueda;
import Arboles.ArbolMViasBusqueda;
import Arboles.IArbolBusqueda;
import excepciones.ExcepcionOrdenInvalido;

/**
 *
 * @author HAROLD
 */
public class practicoArboles {
//• El proyecto se debe almacenar en un repositorio en github (Para esto puede usar una 
//cuenta que ya disponga o crearse una)
//• Incluir un programa en consola que haga la prueba de los métodos resueltos indicando 
//mediante mensajes a que pregunta es respuesta cada método probado
//• En la plataforma copiar el enlace para descargarse el repositorio. Asegurarse que su 
//repositorio tiene acceso de lectura al público. En caso de que su repositorio no se 
//pueda descargar al momento de la revisión no se tomará en cuenta su trabajo.
//• En cada método implementado como respuesta a las preguntas colocar el comentario 
//de a que pregunta corresponde la respuesta.
//• NO SE ACEPTARÁ PROYECTOS COMPRIMIDOS
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExcepcionOrdenInvalido {
        ArbolBinarioBusqueda<Integer,Integer> arbolBinario = new ArbolBinarioBusqueda<>();
        arbolBinario.insertar(40, 21);
        arbolBinario.insertar(30, 21);arbolBinario.insertar(70, 21);
        arbolBinario.insertar(15, 21);arbolBinario.insertar(35, 21);
        arbolBinario.insertar(55, 21);arbolBinario.insertar(87, 21);
        arbolBinario.insertar(48, 21);arbolBinario.insertar(63, 21);

        AVL<Integer,Integer> arbolAVL = new AVL<>();
        arbolAVL.insertar(3, 1); arbolAVL.insertar(2, 1); arbolAVL.insertar(1, 1);
        arbolAVL.insertar(4, 1); arbolAVL.insertar(5, 1);
        
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

        ArbolB<Integer,Integer> arbolB = new ArbolB<>(4);
        arbolB.insertar(300, 10); arbolB.insertar(500, 10); 
        arbolB.insertar(100, 10);
        arbolB.insertar(50, 10); arbolB.insertar(400, 10); arbolB.insertar(800, 10);
        arbolB.insertar(90, 10); arbolB.insertar(91, 10); arbolB.insertar(70, 10);
        arbolB.insertar(75, 10); arbolB.insertar(99, 10); 

        System.out.println("1. Implementar los métodos que no se implementaron en clases o que se implementaron a \n" +
"medias de árboles m vias de búsqueda y arboles B");
        // ARBOL BINARIO
        System.out.println(" \nARBOL BINARIO ");
        System.out.println("SIXE arbolBinario " + arbolBinario.sixe());
        System.out.println("Cantidad hojas recursivo " + arbolBinario.cantidadHojasRec());
        System.out.println("Recorrido por Niveles" + arbolBinario.recorridoPorNiveles());
        System.out.println("Recorrido en PreOrden" + arbolBinario.recorridoEnPreOrden());
        System.out.println("Recorrido en PreOrden RE" + arbolBinario.recorridoEnPreOrdenRe());
        System.out.println("Recorrido en InOrden" + arbolBinario.recorridoEnInOrden());
        System.out.println("Recorrido en InOrden RE" + arbolBinario.recorridoEnInOrdenRe());
        System.out.println("Recorrido en PostOrden" + arbolBinario.recorridoEnPostOrden());
        System.out.println("Recorrido en PostOrden RE" + arbolBinario.recorridoEnPostOrdenRe());
        //arbol AVL 
        System.out.println(" \nARBOL AVL ");
        System.out.println("Sixe AVL " + arbolAVL.sixe());
  //      System.out.println("Nivel AVL " + arbolAVL.nivel());
        System.out.println("Recorrido por Niveles" + arbolAVL.recorridoPorNiveles());
        System.out.println("Recorrido en Pre Orden " + arbolAVL.recorridoEnPreOrden());
        System.out.println("Recorrido en In Orden " + arbolAVL.recorridoEnInOrden());
        System.out.println("Recorrido en Post Orden " + arbolAVL.recorridoEnPostOrden());
        // ARBOL MVIAS
        System.out.println(" \nARBOL MVIAS ");
        System.out.println("Sixe Arbol Mvias" + arbolMvias.sixe());
        System.out.println("Nivel Arbol Mvias" + arbolMvias.nivel());
        System.out.println("Recorrido por Niveles" + arbolMvias.recorridoPorNiveles());
        System.out.println("Recorrido en Pre Orden " + arbolMvias.recorridoEnPreOrden());
        System.out.println("Recorrido en In Orden " + arbolMvias.recorridoEnInOrden());
        System.out.println("Recorrido en Post Orden " + arbolMvias.recorridoEnPostOrden());
        // ARBOL B
        System.out.println(" \nARBOL B ");
        System.out.println("sixe ArbolB " + arbolB.sixe());
        System.out.println("Nivel ArbolB " + arbolB.nivel());
        System.out.println("Recorrido por Niveles" + arbolB.recorridoPorNiveles());
        System.out.println("Recorrido en InOrden ArbolB " + arbolB.recorridoEnInOrden());
        System.out.println("Recorrido por Niveles ArbolB " + arbolB.recorridoPorNiveles());
        System.out.println("Recorrido en Post Orden ArbolB " + arbolB.recorridoEnPostOrden());
        
        System.out.println("\n2. Implemente un método recursivo que retorne el nivel en que se encuentra una clave que se \n" +
"recibe como parámetro. Devolver -1 si la clave no está en el árbol");
        System.out.println("Clave 40 es nivel 0 = "  + arbolBinario.pregunta2(40));
        System.out.println("Clave 70 es nivel 1 = "  + arbolBinario.pregunta2(70));
        System.out.println("Clave 35 es nivel 2 = "  + arbolBinario.pregunta2(35));
        System.out.println("Clave 63 es nivel 3 = "  + arbolBinario.pregunta2(63));
        
        System.out.println("\n3. Implemente un método recursivo que retorne la cantidad de datos no vacíos que hay en el \n" +
"nivel N de un árbol m-vias de búsqueda");
        System.out.println("Nivel 1 -> 11 claves =  " + arbolMvias.pregunta3(1));
        System.out.println("Nivel 2 -> 5 claves =  " + arbolMvias.pregunta3(2));
        System.out.println("Nivel 3 -> 1 claves =  " + arbolMvias.pregunta3(3));
        
        System.out.println("\n4. Implemente un método recursivo que retorne la cantidad de nodos hojas en un árbol m vías \n" +
"de búsqueda, pero solo después del nivel N");
        System.out.println("Despues de nivel 0 -> 2 hijos =  " + arbolMvias.pregunta4(0));
        System.out.println("Despues de nivel 1 -> 2 hijos =  " + arbolMvias.pregunta4(1));
        System.out.println("Despues de nivel 2 -> 1 hijos =  " + arbolMvias.pregunta4(2));  
        
        System.out.println("\n5. Implemente un método iterativo que retorne la cantidad de datos vacios y no vacíos en un \n" +
"árbol b, pero solo antes del nivel N");
        System.out.println("Antes de nivel 1 -> 2 vacios =  " + arbolB.pregunta5datosVacios(1));
        System.out.println("Antes de nivel 2 -> 5 vacios =  " + arbolB.pregunta5datosVacios(2));
        System.out.println("Antes de nivel 3 -> 13 vacios =  " + arbolB.pregunta5datosVacios(3));
        System.out.println("Antes de nivel 1 -> 1 claves =  " + arbolB.pregunta5datosNoVacios(1));
        System.out.println("Antes de nivel 2 -> 4 vacios =  " + arbolB.pregunta5datosNoVacios(2));
        System.out.println("Antes de nivel 3 -> 11 vacios =  " + arbolB.pregunta5datosNoVacios(3));
        
        System.out.println("\n6. Implemente un método que retorne verdadero si solo hay hojas en el último nivel de un \n" +
"árbol m-vias de búsqueda. Falso en caso contrario.");
        System.out.println("arbol =  " + arbolMvias.pregunta6());  
        
        System.out.println("\n7. Implemente un método que retorne verdadero si un árbol m-vias esta balanceado según las \n" +
"reglas de un árbol B. Falso en caso contrario.");
        System.out.println("Falso no esta balanceado ->  =  " + arbolMvias.pregunta7());
        System.out.println("\n8. Implemente un método privado que reciba un dato como parámetro y que retorne cual sería\n" +
"el sucesor inorden de dicho dato, sin realizar el recorrido en inOrden.");
        System.out.println("Sucesor de 150 -> 158 =  " + arbolMvias.pregunta8(150));
        System.out.println("Sucesor de 500 -> 560 =  " + arbolMvias.pregunta8(500));
        System.out.println("Sucesor de 560 -> null =  " + arbolMvias.pregunta8(560));
        System.out.println("\n9. Para un árbol b implemente un método que retorne verdadero si todos sus nodos no hojas \n" +
"no tienen datos vacíos, falso en caso contrario");
        System.out.println("Falso =  " + arbolB.pregunta9());
    }
    
}
