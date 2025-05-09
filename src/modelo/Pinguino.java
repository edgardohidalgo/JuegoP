package modelo;

public class Pinguino extends Jugador {
    private Inventario inventario;
    private  int idJugador;
    public Pinguino(int idJugador ,String nombre, int posicion, String color, Inventario inventario) {
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
    public void añadirItem(){
        inventario.getLista().add(null);
    }
    public void quitaritem(){
        inventario.getLista().remove(inventario.getLista().size()-1);

    }

}