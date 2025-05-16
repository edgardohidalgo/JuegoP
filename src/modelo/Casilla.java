package modelo;

import java.util.ArrayList;

/**
 * Clase abstracta que representa una casilla en el tablero del juego.
 */
public abstract class Casilla {
    private int idCasilla;
    private int posicion;
    private ArrayList<Jugador> jugadoresActuales;

    /**
     * Constructor de la clase Casilla.
     *
     * @param idCasilla          Identificador de la casilla.
     * @param posicion           Posición de la casilla en el tablero.
     * @param jugadoresActuales  Lista de jugadores que se encuentran en la casilla.
     */
    public Casilla(int idCasilla , int posicion , ArrayList<Jugador> jugadoresActuales) {
        this.posicion = posicion;
        this.jugadoresActuales = jugadoresActuales;
        this.idCasilla = idCasilla;
    }

    /**
     * Obtiene el ID de la casilla.
     *
     * @return ID de la casilla.
     */
    public int getIdCasilla() {
        return idCasilla;
    }

    /**
     * Establece el ID de la casilla.
     *
     * @param idCasilla Nuevo ID de la casilla.
     */
    public void setIdCasilla(int idCasilla) {
        this.idCasilla = idCasilla;
    }

    /**
     * Obtiene la posición de la casilla en el tablero.
     *
     * @return Posición de la casilla.
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * Establece la posición de la casilla.
     *
     * @param posicion Nueva posición.
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    /**
     * Obtiene la lista de jugadores que están actualmente en la casilla.
     *
     * @return Lista de jugadores actuales.
     */
    public ArrayList<Jugador> getJugadoresActuales() {
        return jugadoresActuales;
    }

    /**
     * Establece la lista de jugadores en la casilla.
     *
     * @param jugadoresActuales Nueva lista de jugadores.
     */
    public void setJugadoresActuales(ArrayList<Jugador> jugadoresActuales) {
        this.jugadoresActuales = jugadoresActuales;
    }

    /**
     * Añade un jugador a la lista de jugadores actuales.
     *
     * @param jugador Jugador a añadir.
     */
    public void anadirJugador(Jugador jugador) {
        this.jugadoresActuales.add(jugador);
    }

    /**
     * Elimina un jugador de la lista de jugadores actuales.
     *
     * @param jugador Jugador a eliminar.
     */
    public void eliminarJugador(Jugador jugador) {
        this.jugadoresActuales.remove(jugador);
    }

    /**
     * Ejecuta la acción específica de la casilla sobre el jugador.
     *
     * @param jugador Jugador sobre el cual se realiza la acción.
     * @return Nueva posición del jugador después de la acción.
     */
    public abstract int realizarAccion(Jugador jugador);
}
