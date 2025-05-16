package modelo;

import java.util.ArrayList;

/**
 * Casilla sin efecto especial.
 */
public class CasillaNormal extends Casilla {

    /**
     * Constructor de CasillaNormal.
     *
     * @param id        ID de la casilla.
     * @param posicion  Posición en el tablero.
     * @param jugadores Lista de jugadores en esta casilla.
     */
    public CasillaNormal(int id, int posicion, ArrayList<Jugador> jugadores) {
        super(id, posicion, jugadores);
    }

    /**
     * No realiza ninguna acción.
     *
     * @param jugador Jugador afectado.
     * @return 0 (sin efecto).
     */
    @Override
    public int realizarAccion(Jugador jugador) {
        return 0;
    }
}
