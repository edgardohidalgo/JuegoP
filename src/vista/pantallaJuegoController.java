package vista;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import modelo.*;

public class pantallaJuegoController {

    // Menu items
    @FXML private MenuItem newGame;
    @FXML private MenuItem saveGame;
    @FXML private MenuItem loadGame;
    @FXML private MenuItem quitGame;

    // Buttons
    @FXML private Button dado;
    @FXML private Button rapido;
    @FXML private Button lento;
    @FXML private Button peces;
    @FXML private Button nieve;

    // Texts
    @FXML private Text dadoResultText;
    @FXML private Text rapido_t;
    @FXML private Text lento_t;
    @FXML private Text peces_t;
    @FXML private Text nieve_t;
    @FXML private Text eventos;

    // Game board and player pieces
    @FXML private GridPane tablero;
    @FXML private Circle P1;


    // Game objects
    private Tablero tableroJuego;
    private Jugador jugador1;

    private final int COLUMNS = 5;

    @FXML
    private void initialize() {
        // Initialize player
        jugador1 = new Jugador(1, "ed", 0, "rojo");

        // Create a list of players (if you want multiple players)
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador1);

        // Initialize the board and assign players
        tableroJuego = new Tablero(1, new ArrayList<>(), jugadores, 0, jugador1);

        // Initialize the casillas (this will add Casilla objects to the board)
        tableroJuego.inicializarCasillas();



        // Display an initial message
        eventos.setText("¡El juego ha comenzado!");
    }

    @FXML
    private void handleDado(ActionEvent event) {
        Random rand = new Random();
        int diceResult = rand.nextInt(6) + 1;

        // Update the Text
        dadoResultText.setText("Ha salido: " + diceResult);

        // Move the player and execute the casilla action
        moveJugador(jugador1, diceResult);
    }

    private void moveJugador(Jugador jugador, int steps) {
        int currentPos = jugador.getPosicion();
        int nuevaPos = Math.min(currentPos + steps, 49);
        jugador.setPosicion(nuevaPos);

        Casilla casillaActual = tableroJuego.getCasillas().get(nuevaPos);
        int posicionDespues = casillaActual.realizarAccion(jugador);

        // Si la acción de la casilla cambió la posición, actualizamos de nuevo
        if (posicionDespues != nuevaPos) {
            jugador.setPosicion(posicionDespues);

            // Ejecutamos también la acción de la nueva casilla si es distinta
            Casilla nuevaCasilla = tableroJuego.getCasillas().get(posicionDespues);
            nuevaCasilla.realizarAccion(jugador);
        }

        actualizarInterfaz();
    }



    private void actualizarInterfaz() {
        int posFinal = jugador1.getPosicion();
        int row = posFinal / COLUMNS;
        int col = posFinal % COLUMNS;

        GridPane.setRowIndex(P1, row);
        GridPane.setColumnIndex(P1, col);

        eventos.setText("Estás en la casilla " + posFinal + " y se ejecutó una acción.");
    }


    @FXML
    private void handleRapido() {
        System.out.println("Fast.");
        // TODO: Implement fast movement or event
    }

    @FXML
    private void handleLento() {
        System.out.println("Slow.");
        // TODO: Implement slow movement or event
    }

    @FXML
    private void handlePeces() {
        System.out.println("Fish.");
        // TODO: Implement fish event
    }

    @FXML
    private void handleNieve() {
        System.out.println("Snow.");
        // TODO: Implement snow event
    }
}