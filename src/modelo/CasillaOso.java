package modelo;

import java.util.ArrayList;

public class CasillaOso extends Casilla {
    public CasillaOso(int idCasilla ,int posicion , ArrayList<Jugador> jugadoresActuales) {
        super(idCasilla, posicion , jugadoresActuales);
    }
    @Override
    public void realizarAccion() {
        for (Jugador j : getJugadoresActuales()) {
            if (j instanceof Pinguino) {
                j.moverse(0);
            }
        }
    }
}

