package modelo;

import java.util.ArrayList;

public class CasillaSueloQuebradizo extends Casilla {

    public CasillaSueloQuebradizo(int idCasilla ,int posicion , ArrayList<Jugador> jugadoresActuales) {
        super(idCasilla, posicion , jugadoresActuales);
    }

    @Override
    public int realizarAccion(Jugador jugador) {
        if (jugador instanceof Pinguino) {
            Pinguino p = (Pinguino) jugador;
            if (p.getInventario().getLista().isEmpty()) {
                // Si no tiene objetos en el inventario, nada ocurre
            } else if (p.getInventario().getLista().size() <= 5) {
                // Si tiene entre 1 y 5 objetos, retrocede una casilla
                int nuevaPos = Math.max(jugador.getPosicion() - 1, 0);
                jugador.setPosicion(nuevaPos);
                return nuevaPos;
            } else {
                // Si tiene más de 5 objetos, avanza una casilla
                int nuevaPos = Math.min(jugador.getPosicion() + 1, 49);
                jugador.setPosicion(nuevaPos);
                return nuevaPos;
            }
        }
        return jugador.getPosicion();
    }
}