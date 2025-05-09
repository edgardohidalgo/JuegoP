package vista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import modelo.*;

import java.util.ArrayList;
import java.util.Random;

public class pantallaJuegoController {

    // FXML elementos
    @FXML private MenuItem newGame;
    @FXML private MenuItem saveGame;
    @FXML private MenuItem loadGame;
    @FXML private MenuItem quitGame;

    @FXML private Button dado;
    @FXML private Button rapido;
    @FXML private Button lento;
    @FXML private Button peces;
    @FXML private Button nieve;

    @FXML private Text dadoResultText;
    @FXML private Text rapido_t;
    @FXML private Text lento_t;
    @FXML private Text peces_t;
    @FXML private Text nieve_t;
    @FXML private Text eventos;

    @FXML private GridPane tablero;
    @FXML private Circle P1;
    @FXML private Circle P2;
    @FXML private Circle P3;
    @FXML private Circle P4;

    private final int COLUMNS = 5;

    // MODELO
    private Tablero tableroModelo;
    private Jugador jugador;

    @FXML
    private void initialize() {
        eventos.setText("¡El juego ha comenzado!");

        // Inicializamos el tablero con casillas y jugador
        jugador = new Jugador(1, "Jugador 1", 0, null); // ejemplo
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);

        tableroModelo = new Tablero(1, new ArrayList<>(), jugadores, 0, jugador);
        tableroModelo.inicializarCasillas();

        System.out.println("Tablero y casillas inicializados.");
    }

    @FXML
    private void handleDado(ActionEvent event) {
        Random rand = new Random();
        int diceResult = rand.nextInt(6) + 1;
        dadoResultText.setText("Ha salido: " + diceResult);

        moverJugador(diceResult);
    }

    private void moverJugador(int pasos) {
        int posicionActual = jugador.getPosicion();
        int nuevaPosicion = posicionActual + pasos;

        if (nuevaPosicion >= 50) {
            nuevaPosicion = 49;
        }

        jugador.setPosicion(nuevaPosicion);

        // Mover visualmente el círculo
        int fila = nuevaPosicion / COLUMNS;
        int columna = nuevaPosicion % COLUMNS;

        GridPane.setRowIndex(P1, fila);
        GridPane.setColumnIndex(P1, columna);

        System.out.println("Jugador ahora en posición " + nuevaPosicion);

        // Ejecutar la acción de la casilla
        Casilla casillaActual = tableroModelo.getCasillas().get(nuevaPosicion);
        casillaActual.getJugadoresActuales().clear();
        casillaActual.getJugadoresActuales().add(jugador);

        casillaActual.realizarAccion(jugador);

        eventos.setText("Casilla " + nuevaPosicion + ": " + casillaActual.getClass().getSimpleName());
    }

    // Los otros botones aún están por implementar
    @FXML
    private void handleRapido() {
        System.out.println("Fast.");
    }

    @FXML
    private void handleLento() {
        System.out.println("Slow.");
    }

    @FXML
    private void handlePeces() {
        System.out.println("Fish.");
    }

    @FXML
    private void handleNieve() {
        System.out.println("Snow.");
    }
}
