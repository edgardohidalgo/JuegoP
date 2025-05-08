package modelo;

import java.util.ArrayList;

public class CasillaSueloQuebradizo extends Casilla {
    public CasillaSueloQuebradizo(int idCasilla ,int posicion , ArrayList<Jugador> jugadoresActuales) {
        super(idCasilla, posicion , jugadoresActuales);
    }
    @Override
    public void realizarAccion() {
        for(Jugador j : getJugadoresActuales()) {
            if(j instanceof Pinguino) {
                if(((Pinguino) j).getInventario().getLista().size() == 0) {

                } else if(((Pinguino) j).getInventario().getLista().size() > 0 && ((Pinguino) j).getInventario().getLista().size() < 5 ) {

                } else if(((Pinguino) j).getInventario().getLista().size() > 5) {

                }
            }
        }
    }



}
