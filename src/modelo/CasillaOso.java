package modelo;

import java.util.ArrayList;

public class CasillaOso extends Casilla {

    public CasillaOso(int idCasilla ,int posicion, ArrayList<Jugador> jugadoresActuales) {
        super(idCasilla, posicion, jugadoresActuales);
    }

    @Override
    public int realizarAccion(Jugador jugador) {
        if (jugador instanceof Pinguino) {
            // Se devuelve al inicio del tablero
            jugador.setPosicion(0);
            return 0;
        }
        return jugador.getPosicion();
    }
}
