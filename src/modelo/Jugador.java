package modelo;

/**
 * Clase que representa a un jugador en el juego.
 */
public class Jugador {
    private int idJugador;
    private String nombre;
    private int posicion;
    private String color;

    /**
     * Constructor del jugador.
     * @param idJugador identificador del jugador
     * @param nombre nombre del jugador
     * @param posicion posición inicial
     * @param color color del jugador
     */
    public Jugador(int idJugador, String nombre, int posicion, String color) {
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

    /**
     * Mueve al jugador una cantidad determinada de posiciones.
     * @param p cantidad a mover
     */
    public void moverse(int p) {
        this.posicion += p;
    }

    /**
     * Método para lanzar un dado (vacío, puede ser sobrescrito).
     * @param maximoDado valor máximo del dado
     */
    public void tirarDado(int maximoDado) {
        // Por implementar
    }
}
