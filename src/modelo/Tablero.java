package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tablero {
    private int idTablero;
    private ArrayList<Casilla> Casillas;
    private ArrayList<Jugador> jugadores;
    private int turnos;
    private Jugador jugador;

    public Tablero(int idTablero, ArrayList<Casilla> Casillas, ArrayList<Jugador> jugadores, int turnos, Jugador jugador) {
        this.idTablero = idTablero;
        this.Casillas = Casillas;
        this.jugadores = jugadores;
        this.turnos = turnos;
        this.jugador = jugador;
    }

    public int getIdTablero() {
        return idTablero;
    }

    public void setIdTablero(int idTablero) {
        this.idTablero = idTablero;
    }

    public ArrayList<Casilla> getCasillas() {
        return Casillas;
    }

    public void setCasillas(ArrayList<Casilla> Casillas) {
        this.Casillas = Casillas;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void inicializarCasillas() {
        Casillas = new ArrayList<>();

        // Puedes cambiar el número y tipo de casillas según tu lógica de juego
        for (int i = 0; i < 50; i++) {
            ArrayList<Jugador> jugadoresEnCasilla = new ArrayList<>();

            Casilla casilla;
            if (i % 5 == 0) {
                // Evento con acciones predeterminadas
                casilla = new CasillaEvento(i, i, jugadoresEnCasilla, new String[]{"pez", "bolas", "rapido", "lento", "pierdeTurno", "pierdeItem", "motos"});
            } else if (i % 7 == 0) {
                casilla = new CasillaOso(i, i, jugadoresEnCasilla);
            } else if (i % 4 == 0) {
                casilla = new CasillaSueloQuebradizo(i, i, jugadoresEnCasilla);
            } else {
                // Casilla normal sin evento
                casilla = new CasillaAgujero(i, i, jugadoresEnCasilla);
            }

            Casillas.add(casilla);
            System.out.println("Casilla " + i + " inicializada: " + casilla.getClass().getSimpleName());

        }

    }


    // Método para actualizar el tablero (lo puedes personalizar más adelante)
    public void actualizarTablero() {
        // Lógica para actualizar el tablero si es necesario
    }
}
