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

    // Game logic
    private Tablero tableroJuego;  // Usamos el nombre correcto de la clase
    private Jugador jugador1; // Ahora es una instancia de Jugador

    private final int COLUMNS = 5;

    @FXML
    private void initialize() {
        eventos.setText("¡El juego ha comenzado!");

        // Inicializa jugadores y tablero
        jugador1 = new Jugador(1,"ed" , 0 , "rojo");
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador1);

        tableroJuego = new Tablero(1, new ArrayList<>(), jugadores, 0, jugador1);
        tableroJuego.inicializarCasillas(); // Inicializa las casillas del tablero
    }

    @FXML
    private void handleDado(ActionEvent event) {
        Random rand = new Random();
        int diceResult = rand.nextInt(6) + 1;  // Genera un número aleatorio entre 1 y 6

        dadoResultText.setText("Ha salido: " + diceResult);
        moveJugador(jugador1, diceResult); // Mueve al jugador y ejecuta la acción de la casilla
    }

    private void moveJugador(Jugador jugador, int steps) {
        // Eliminar jugador de la casilla anterior
        tableroJuego.getCasillas().get(jugador.getPosicion()).eliminarJugador(jugador);

        jugador.moverse(steps); // Mueve al jugador de acuerdo al dado

        // Asegúrate de no exceder las 50 casillas
        if (jugador.getPosicion() >= 50) jugador.setPosicion(49);

        // Añadir jugador a la nueva casilla
        Casilla nuevaCasilla = tableroJuego.getCasillas().get(jugador.getPosicion());
        nuevaCasilla.anadirJugador(jugador);

        // Actualizar la posición gráfica del jugador (P1)
        int row = jugador.getPosicion() / COLUMNS;
        int col = jugador.getPosicion() % COLUMNS;
        GridPane.setRowIndex(P1, row);
        GridPane.setColumnIndex(P1, col);

        // Ejecutar acción de la casilla
        nuevaCasilla.realizarAccion();
        eventos.setText("Estás en la casilla " + jugador.getPosicion() + " y se ejecutó una acción.");
    }

    @FXML
    private void handleRapido() {
        System.out.println("Fast.");
        // Implementa la lógica para moverse más rápido (si aplicable)
    }

    @FXML
    private void handleLento() {
        System.out.println("Slow.");
        // Implementa la lógica para moverse más lentamente (si aplicable)
    }

    @FXML
    private void handlePeces() {
        System.out.println("Fish.");
        // Implementa la lógica relacionada con el evento "Peces"
    }

    @FXML
    private void handleNieve() {
        System.out.println("Snow.");
        // Implementa la lógica relacionada con el evento "Nieve"
    }
}
