package modelo;

import java.util.ArrayList;

public class Tablero {
    private int idTablero;
    private ArrayList<Casilla> Casillas;
    private ArrayList<Jugador> jugadores;
    private  int turnos;
    private Jugador jugador;


    public Tablero(int idTablero , ArrayList<Casilla> Casillas, ArrayList<Jugador> jugadores, int turnos , Jugador jugador ){
        this.idTablero = idTablero;
        this.Casillas = Casillas;
        this.jugadores = jugadores;
        this.turnos=turnos;
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


    public void actualizarTablero() {

    }




}
