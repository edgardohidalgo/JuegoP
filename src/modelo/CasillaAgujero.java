package modelo;

import java.util.ArrayList;

public class CasillaAgujero extends Casilla {

    public CasillaAgujero(int idCasilla ,int posicion , ArrayList<Jugador> jugadoresActuales) {
        super(idCasilla, posicion , jugadoresActuales);
    }

    @Override
    public int realizarAccion(Jugador jugador) {
        if (jugador instanceof Pinguino) {
            int nuevaPos = Math.max(jugador.getPosicion() - 1, 0); // Asegura que no se mueva fuera del tablero
            jugador.setPosicion(nuevaPos);
            return nuevaPos;
        }
        return jugador.getPosicion();
    }
}
