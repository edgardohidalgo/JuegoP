package modelo;

import java.util.ArrayList;

public class CasillaNormal extends Casilla {
    public CasillaNormal(int id, int posicion, ArrayList<Jugador> jugadores) {
        super(id, posicion, jugadores);
    }

    @Override
    public int realizarAccion(Jugador jugador) {
        return 0; // Sin efecto especial
    }
}
