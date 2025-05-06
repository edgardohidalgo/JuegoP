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
    public void gestionarBatalla(){

    }
    public void usarObjeto() {

    }
    public void añadirItem(){

    }
    public void quitaritem(){

    }

}
