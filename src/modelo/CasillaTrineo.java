package modelo;

import java.util.ArrayList;

/**
 * Casilla que transporta al jugador a la próxima casilla Trineo disponible.
 */
public class CasillaTrineo extends Casilla {
    private Tablero tablero;

    /**
     * Constructor de CasillaTrineo.
     *
     * @param idCasilla          ID de la casilla.
     * @param posicion           Posición de la casilla.
     * @param jugadoresActuales  Jugadores en la casilla.
     * @param tablero            Instancia del tablero de juego.
     */
    public CasillaTrineo(int idCasilla, int posicion, ArrayList<Jugador> jugadoresActuales, Tablero tablero) {
        super(idCasilla, posicion, jugadoresActuales);
        this.tablero = tablero;
    }

    /**
     * Mueve al jugador a la próxima casilla Trineo si existe.
     *
     * @param jugador Jugador afectado.
     * @return Nueva posición del jugador.
     */
    @Override
    public int realizarAccion(Jugador jugador) {
        int posActual = getPosicion();
        int nuevaPos = posActual;

        ArrayList<Casilla> casillas = tablero.getCasillas();

        for (int i = posActual + 1; i < casillas.size(); i++) {
            if (casillas.get(i) instanceof CasillaTrineo) {
                nuevaPos = i;
                break;
            }
        }

        if (nuevaPos != posActual) {
            this.eliminarJugador(jugador);
            casillas.get(nuevaPos).anadirJugador(jugador);
            jugador.setPosicion(nuevaPos);
            System.out.println(jugador.getNombre() + " ha viajado en trineo a la casilla " + nuevaPos);
        } else {
            System.out.println(jugador.getNombre() + " no ha encontrado otro trineo. Se queda en la misma casilla.");
        }

        return nuevaPos;
    }
}
