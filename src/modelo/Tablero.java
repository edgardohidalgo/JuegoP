package modelo;

import java.util.ArrayList;
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

    // Método para inicializar las casillas del tablero de forma aleatoria
    public void inicializarCasillas() {
        Random rand = new Random();
        // Limpiar la lista de casillas antes de agregar las nuevas
        Casillas.clear();

        for (int i = 0; i < 50; i++) { // Supongo que el tablero tiene 50 casillas
            Casilla casilla;

            // Generar un número aleatorio entre 0 y 3 para elegir el tipo de casilla
            int tipoCasilla = rand.nextInt(7); // 0: CasillaEvento, 1: CasillaAgujero, 2: CasillaOso, 3: CasillaSueloQuebradizo

            switch (tipoCasilla) {
                case 0:
                    casilla = new CasillaEvento(i, i, new ArrayList<>() ); // Cambié el tipoEvento aquí
                    break;
                case 1:
                    casilla = new CasillaAgujero(i, i, new ArrayList<>());
                    break;
                case 2:
                    casilla = new CasillaOso(i, i, new ArrayList<>());
                    break;

                case 3:
                    casilla = new CasillaTrineo(i, i, new ArrayList<>() , this);
                    break;

                case 4:
                    casilla = new CasillaNormal(i,i,new ArrayList<>()) ;
                    break;

                case 5:
                    casilla = new CasillaNormal(i,i,new ArrayList<>()) ;
                    break;

                case 6:
                    casilla = new CasillaNormal(i,i,new ArrayList<>()) ;
                    break;
                default:
                    casilla = new Casilla(i, i, new ArrayList<>()) {
                        @Override
                        public int realizarAccion(Jugador jugador) {
                            return 0;
                        }
                    };
                    break;
            }

            // Añadir la casilla generada a la lista de casillas del tablero
            Casillas.add(casilla);
            System.out.println("Casilla " + i + " inicializada: " + casilla.getClass().getSimpleName());
        }
    }

    // Método para actualizar el tablero (lo puedes personalizar más adelante)
    public void actualizarTablero() {

        // Lógica para actualizar el tablero si es necesario
    }
}
