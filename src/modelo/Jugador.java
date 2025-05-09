package modelo;

public class Jugador {
    private int idJugador;
    private String nombre;
    private int posicion;
    private String color;

    /**
     *
     * @param nombre
     * @param posicion
     * @param color
     *
     */
    public Jugador( int idJugador ,String nombre, int posicion, String color) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.color = color;
        this.idJugador = idJugador;
    }

 

    public int getIdJugador() {
        return idJugador;
    }
    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void moverse(int p) {
        this.posicion += p;
    }

    public void tirarDado(int maximoDado) {

    }

}
