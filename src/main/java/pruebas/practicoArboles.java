/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

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
        System.out.println(arbolBinario.toString());
        System.out.println(arbolBinario.sixe());
        System.out.println("Cantidad hojas recu " + arbolBinario.cantidadHojasRec());
        System.out.println("Recorrido por Nieveles" + arbolBinario.recorridoPorNiveles());
        System.out.println("Recorrido en PreOrden" + arbolBinario.recorridoEnPreOrden());
        System.out.println("Recorrido en InOrden" + arbolBinario.recorridoEnInOrden());
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
        System.out.println("\nRecorrido en Pre Orden " + arbolMvias.recorridoEnPreOrden());
        System.out.println("Recorrido en In Orden " + arbolMvias.recorridoEnInOrden());
        System.out.println("Recorrido en Post Orden " + arbolMvias.recorridoEnPostOrden());
        System.out.println("Sixe " + arbolMvias.sixe());
        System.out.println("Nivel " + arbolMvias.nivel());
        System.out.println("1. Implementar los métodos que no se implementaron en clases o que se implementaron a \n" +
"medias de árboles m vias de búsqueda y arboles B");
        
        System.out.println("\n2. Implemente un método recursivo que retorne el nivel en que se encuentra una clave que se \n" +
"recibe como parámetro. Devolver -1 si la clave no está en el árbol");
        System.out.println("Clvae 40 es nivel 0 = "  + arbolBinario.pregunta2(40));
        System.out.println("Clvae 70 es nivel 1 = "  + arbolBinario.pregunta2(70));
        System.out.println("Clvae 35 es nivel 2 = "  + arbolBinario.pregunta2(35));
        System.out.println("Clvae 63 es nivel 3 = "  + arbolBinario.pregunta2(63));
        
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
        
        System.out.println("\n6. Implemente un método que retorne verdadero si solo hay hojas en el último nivel de un \n" +
"árbol m-vias de búsqueda. Falso en caso contrario.");
        
        System.out.println("\n7. Implemente un método que retorne verdadero si un árbol m-vias esta balanceado según las \n" +
"reglas de un árbol B. Falso en caso contrario.");
        
        System.out.println("\n8. Implemente un método privado que reciba un dato como parámetro y que retorne cual sería\n" +
"el sucesor inorden de dicho dato, sin realizar el recorrido en inOrden.");
        
        System.out.println("\n9. Para un árbol b implemente un método que retorne verdadero si todos sus nodos no hojas \n" +
"no tienen datos vacíos, falso en caso contrario");
    }
    
}
