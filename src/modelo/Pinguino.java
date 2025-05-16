package modelo;

/**
 * Clase que representa a un pingüino, un tipo especial de jugador con inventario.
 */
public class Pinguino extends Jugador {
    private Inventario inventario;
    private int idJugador;

    /**
     * Constructor del pingüino.
     * @param idJugador identificador
     * @param nombre nombre
     * @param posicion posición inicial
     * @param color color del jugador
     * @param inventario inventario asociado
     */
    public Pinguino(int idJugador, String nombre, int posicion, String color, Inventario inventario) {
        super(idJugador, nombre, posicion, color);
        this.inventario = inventario;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    /**
     * Usa un objeto del inventario (elimina el primero).
     */
    public void usarObjeto() {
        quitaritem();
    }

    /**
     * Añade un item al inventario.
     * @param nombreItem nombre del item a añadir
     */
    public void añadirItem(String nombreItem) {
        if (inventario != null) {
            inventario.agregarItem(new Item(nombreItem, 1));
        }
    }

    /**
     * Quita el primer item del inventario.
     */
    public void quitaritem() {
        if (inventario != null && !inventario.getLista().isEmpty()) {
            inventario.eliminarItem(0);
        }
    }
}
