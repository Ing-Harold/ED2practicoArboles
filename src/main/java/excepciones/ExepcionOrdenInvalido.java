/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

/**
 *
 * @author HAROLD
 */
public class ExepcionOrdenInvalido extends Exception {

    /**
     * Creates a new instance of <code>ExepcionOrdenInvalido</code> without
     * detail message.
     */
    public ExepcionOrdenInvalido() {
        super("Clave no existe en el arbol");
    }

    /**
     * Constructs an instance of <code>ExepcionOrdenInvalido</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExepcionOrdenInvalido(String msg) {
        super(msg);
    }
}
