package modelo;

import java.util.ArrayList;
import java.util.Random;

public class CasillaEvento extends Casilla {
    private String[] eventos = { "pez", "bolas", "rapido", "lento", "pierdeTurno", "pierdeItem", "motos" };

    public CasillaEvento(int idCasilla ,int posicion , ArrayList<Jugador> jugadoresActuales , String[] eventos) {
        super(idCasilla, posicion , jugadoresActuales);
    }

    @Override
    public int realizarAccion(Jugador jugador) {
        if (jugador instanceof Pinguino) {
            Random ran = new Random();
            int pos = ran.nextInt(7); // Random entre 0 y 6

            switch (eventos[pos]) {
                case "pez":
                    // Acción de "pez"
                    break;
                case "bolas":
                    ((Pinguino) jugador).añadirItem();
                    break;
                case "rapido":
                    // Implementar movimiento rápido
                    break;
                case "lento":
                    // Implementar movimiento lento
                    break;
                case "pierdeTurno":
                    // Implementar pérdida de turno
                    break;
                case "pierdeItem":
                    ((Pinguino) jugador).quitaritem();
                    break;
                case "motos":
                    // Implementar motos (quizás movimiento extra o algo específico)
                    break;
            }
            return jugador.getPosicion(); // No cambia la posición, solo el efecto
        }
        return jugador.getPosicion();
    }
}
