package modelo;

import java.util.ArrayList;

/**
 * Casilla que representa un oso. Envía al pingüino al inicio del tablero.
 */
public class CasillaOso extends Casilla {

    /**
     * Constructor de CasillaOso.
     *
     * @param idCasilla          ID de la casilla.
     * @param posicion           Posición de la casilla.
     * @param jugadoresActuales  Lista de jugadores actuales.
     */
    public CasillaOso(int idCasilla ,int posicion, ArrayList<Jugador> jugadoresActuales) {
        super(idCasilla, posicion, jugadoresActuales);
    }

    /**
     * Devuelve al pingüino al inicio del tablero.
     *
     * @param jugador Jugador afectado.
     * @return Nueva posición (0) si es pingüino, actual si no lo es.
     */
    @Override
    public int realizarAccion(Jugador jugador) {
        if (jugador instanceof Pinguino) {
            jugador.setPosicion(0);
            return 0;
        }
        return jugador.getPosicion();
    }
}
