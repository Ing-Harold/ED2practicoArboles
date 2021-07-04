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
public class ExcepcionOrdenInvalido extends Exception {

    /**
     * Creates a new instance of <code>ExepcionOrdenInvalido</code> without
     * detail message.
     */
    public ExcepcionOrdenInvalido() {
        super("Clave no existe en el arbol");
    }

    /**
     * Constructs an instance of <code>ExepcionOrdenInvalido</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionOrdenInvalido(String msg) {
        super(msg);
    }
}
