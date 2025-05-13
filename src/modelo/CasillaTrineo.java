package modelo;

import java.util.ArrayList;

public class CasillaTrineo extends Casilla {
    private Tablero tablero;  // 👈 Aquí guardas el tablero

    public CasillaTrineo(int idCasilla, int posicion, ArrayList<Jugador> jugadoresActuales, Tablero tablero) {
        super(idCasilla, posicion, jugadoresActuales);
        this.tablero = tablero;
    }

    @Override
    public int realizarAccion(Jugador jugador) {
        int posActual = getPosicion();
        int nuevaPos = posActual;

        ArrayList<Casilla> casillas = tablero.getCasillas();  // 👈 Usas la instancia, no la clase

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
