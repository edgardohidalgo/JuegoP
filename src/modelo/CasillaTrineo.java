package modelo;

import java.util.ArrayList;
import java.util.List;

public class CasillaTrineo extends Casilla {
    private List<Integer> posicionsTrineu; 

    public CasillaTrineo(int idCasilla ,int posicion , ArrayList<Jugador> jugadoresActuales , List<Integer> posicionsTrineu) {
        super(idCasilla, posicion , jugadoresActuales);
        this.posicionsTrineu = posicionsTrineu;
    }

    @Override
    public void realizarAccion() {
    }
}