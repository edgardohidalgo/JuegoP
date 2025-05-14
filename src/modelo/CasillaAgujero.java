package modelo;

import java.util.ArrayList;

public class CasillaAgujero extends Casilla {

    public CasillaAgujero(int idCasilla, int posicion, ArrayList<Jugador> jugadoresActuales) {
        super(idCasilla, posicion, jugadoresActuales);
    }

    @Override
    public int realizarAccion(Jugador jugador) {
        if (jugador instanceof Pinguino) {
            // Aseguramos que no retroceda más allá de la posición 0
            int nuevaPos = Math.max(jugador.getPosicion() - 1, 0);

            System.out.println("¡Casilla Agujero! Retrocediendo de " + jugador.getPosicion() + " a " + nuevaPos);

            // Actualizamos la posición del jugador en el modelo
            jugador.setPosicion(nuevaPos);

            // Devolvemos la nueva posición
            return nuevaPos;
        }
        return jugador.getPosicion(); // Si no es un pingüino, mantiene su posición
    }
}