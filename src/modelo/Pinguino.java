package modelo;

public class Pinguino extends Jugador {
    private Inventario inventario;
    private int idJugador;

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

    public void usarObjeto() {
        quitaritem();
    }

    public void añadirItem(String nombreItem) {
        if (inventario != null) {
            // Usar el método agregarItem de Inventario para activar el callback
            inventario.agregarItem(new Item(nombreItem, 1));
        }
    }

    public void quitaritem() {
        if (inventario != null && !inventario.getLista().isEmpty()) {
            // Modificar para usar un método que active el callback
            inventario.eliminarItem(0);
        }
    }
}