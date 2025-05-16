package modelo;

/**
 * Clase que representa un item con un nombre y una cantidad.
 */
public class Item {
    private String nombre;
    private int cantidad;

    /**
     * Constructor del item.
     * @param nombre nombre del item
     * @param cantidad cantidad del item
     */
    public Item(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el nombre del item.
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del item.
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la cantidad del item.
     * @return cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del item.
     * @param cantidad nueva cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
