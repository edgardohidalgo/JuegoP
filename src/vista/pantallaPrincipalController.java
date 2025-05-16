package vista;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import modelo.UsuarioDao;
import modelo.UsuarioDao;

public class pantallaPrincipalController {

    @FXML private MenuItem newGame;
    @FXML private MenuItem saveGame;
    @FXML private MenuItem loadGame;
    @FXML private MenuItem quitGame;

    @FXML private TextField userField;
    @FXML private PasswordField passField;

    @FXML private Button loginButton;
    @FXML private Button registerButton;

    private UsuarioDao usuarioDAO;

    @FXML
    private void initialize() {
        // This method is called automatically after the FXML is loaded
        System.out.println("pantallaPrincipalController initialized");

        // Inicializar el DAO de usuarios
        usuarioDAO = new UsuarioDao();
    }

    @FXML
    private void handleNewGame() {
        System.out.println("New Game clicked");
        // TODO
    }

    @FXML
    private void handleSaveGame() {
        System.out.println("Save Game clicked");
        // TODO
    }

    @FXML
    private void handleLoadGame() {
        System.out.println("Load Game clicked");
        // TODO
    }

    @FXML
    private void handleQuitGame() {
        System.out.println("Quit Game clicked");
        System.exit(0);
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = userField.getText();
        String password = passField.getText();

        System.out.println("Login pressed: " + username + " / " + password);

        // Verificar que los campos no estén vacíos
        if (username.isEmpty() || password.isEmpty()) {
            mostrarAlerta(AlertType.ERROR, "Error de Login", "Por favor, introduce usuario y contraseña.");
            return;
        }

        // Verificar credenciales en la base de datos
        boolean credencialesValidas = usuarioDAO.validarUsuario(username, password);

        if (credencialesValidas) {
            try {
                // Cargar la pantalla de juego
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pantallaJuego.fxml"));
                Parent pantallaJuegoRoot = loader.load();

                Scene pantallaJuegoScene = new Scene(pantallaJuegoRoot);

                // Get the current stage using the event
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(pantallaJuegoScene);
                stage.setTitle("El Juego del Pingüino");
            } catch (Exception e) {
                e.printStackTrace();
                mostrarAlerta(AlertType.ERROR, "Error", "No se pudo cargar la pantalla de juego: " + e.getMessage());
            }
        } else {
            // Credenciales inválidas
            mostrarAlerta(AlertType.ERROR, "Error de Login", "Usuario o contraseña incorrectos.");
        }
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        String username = userField.getText();
        String password = passField.getText();

        System.out.println("Register pressed: " + username);

        // Verificar que los campos no estén vacíos
        if (username.isEmpty() || password.isEmpty()) {
            mostrarAlerta(AlertType.ERROR, "Error de Registro", "Por favor, introduce usuario y contraseña.");
            return;
        }

        // Intentar registrar el usuario
        boolean registroExitoso = usuarioDAO.registrarUsuario(username, password);

        if (registroExitoso) {
            mostrarAlerta(AlertType.INFORMATION, "Registro Exitoso",
                    "Usuario registrado correctamente. Ya puedes iniciar sesión.");
        } else {
            mostrarAlerta(AlertType.ERROR, "Error de Registro",
                    "No se pudo registrar el usuario. Es posible que el nombre de usuario ya exista.");
        }
    }

    /**
     * Muestra una alerta con el tipo, título y mensaje especificados
     */
    private void mostrarAlerta(AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}