package modelo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Casilla que desencadena un evento aleatorio cuando un pingüino cae en ella.
 */
public class CasillaEvento extends Casilla {
    private String[] eventos = { "pez", "bola", "rapido", "lento", "pierdeTurno", "pierdeItem", "motos" };

    /**
     * Constructor de CasillaEvento.
     *
     * @param idCasilla          ID de la casilla.
     * @param posicion           Posición de la casilla.
     * @param jugadoresActuales  Jugadores en esta casilla.
     */
    public CasillaEvento(int idCasilla, int posicion, ArrayList<Jugador> jugadoresActuales) {
        super(idCasilla, posicion, jugadoresActuales);
    }

    /**
     * Ejecuta un evento aleatorio que afecta al pingüino.
     *
     * @param jugador Jugador afectado.
     * @return Posición actual del jugador (sin cambios).
     */
    @Override
    public int realizarAccion(Jugador jugador) {
        if (jugador instanceof Pinguino) {
            Pinguino pinguino = (Pinguino) jugador;
            Random ran = new Random();
            int pos = ran.nextInt(eventos.length);

            System.out.println("Evento seleccionado: " + eventos[pos]);

            switch (eventos[pos]) {
                case "pez":
                    pinguino.añadirItem("pez");
                    System.out.println("¡Has conseguido un pez!");
                    break;
                case "bola":
                    pinguino.añadirItem("bola");
                    System.out.println("¡Has conseguido una bola de nieve!");
                    break;
                case "rapido":
                    pinguino.añadirItem("dado_rapido");
                    System.out.println("¡Has conseguido un dado rápido!");
                    break;
                case "lento":
                    pinguino.añadirItem("dado_lento");
                    System.out.println("¡Has conseguido un dado lento!");
                    break;
                default:
                    System.out.println("Evento sin efectos adicionales");
                    break;
            }
        }
        return jugador.getPosicion();
    }
}
