package modelo;

import java.util.ArrayList;

/**
 * Casilla que representa un agujero. Hace retroceder al pingüino una posición.
 */
public class CasillaAgujero extends Casilla {

    /**
     * Constructor de CasillaAgujero.
     *
     * @param idCasilla          ID de la casilla.
     * @param posicion           Posición en el tablero.
     * @param jugadoresActuales  Lista de jugadores en esta casilla.
     */
    public CasillaAgujero(int idCasilla, int posicion, ArrayList<Jugador> jugadoresActuales) {
        super(idCasilla, posicion, jugadoresActuales);
    }

    /**
     * Hace que el pingüino retroceda una posición, sin ir por debajo de 0.
     *
     * @param jugador Jugador afectado.
     * @return Nueva posición del jugador.
     */
    @Override
    public int realizarAccion(Jugador jugador) {
        if (jugador instanceof Pinguino) {
            int nuevaPos = Math.max(jugador.getPosicion() - 1, 0);
            System.out.println("¡Casilla Agujero! Retrocediendo de " + jugador.getPosicion() + " a " + nuevaPos);
            jugador.setPosicion(nuevaPos);
            return nuevaPos;
        }
        return jugador.getPosicion();
    }
}
