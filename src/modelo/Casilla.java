package modelo;

import java.util.ArrayList;

public abstract class Casilla {
    private int idCasilla;
    private int posicion;
    private ArrayList<Jugador> jugadoresActuales;

    public Casilla(int idCasilla , int posicion , ArrayList<Jugador> jugadoresActuales) {
        this.posicion = posicion;
        this.jugadoresActuales = jugadoresActuales;
        this.idCasilla = idCasilla;
    }

    public int getIdCasilla() {
        return idCasilla;
    }
    public void setIdCasilla(int idCasilla) {
        this.idCasilla = idCasilla;
    }
    public int getPosicion() {
        return posicion;
    }
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    public ArrayList<Jugador> getJugadoresActuales() {
        return jugadoresActuales;
    }
    public void setJugadoresActuales(ArrayList<Jugador> jugadoresActuales) {
        this.jugadoresActuales = jugadoresActuales;
    }

    public void anadirJugador(Jugador jugador) {
        this.jugadoresActuales.add(jugador);
    }

    public void eliminarJugador(Jugador jugador) {
        this.jugadoresActuales.remove(jugador);
    }

    // Modificación: El método ahora devuelve la nueva posición tras la acción.
    public abstract int realizarAccion(Jugador jugador);
}
