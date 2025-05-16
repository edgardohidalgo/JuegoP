package modelo;

import BD.conexioOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase para gestionar operaciones relacionadas con los usuarios
 */
public class UsuarioDao {

    /**
     * Verifica si un usuario existe en la base de datos con las credenciales proporcionadas
     * @param username Nombre de usuario
     * @param password Contraseña
     * @return true si las credenciales son válidas, false en caso contrario
     */
    public boolean validarUsuario(String username, String password) {
        conexioOracle conexion = new conexioOracle();
        Connection conn = conexion.getConn();
        boolean esValido = false;

        try {
            // Consulta SQL para verificar si el usuario existe con esa contraseña
            String sql = "SELECT * FROM Usuario WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            // Si hay al menos un resultado, el usuario existe
            if (rs.next()) {
                esValido = true;
            }

            // Cerrar recursos
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println("Error al validar usuario: " + e.getMessage());
        } finally {
            // Cerrar la conexión
            conexion.desconectar();
        }

        return esValido;
    }

    /**
     * Registra un nuevo usuario en la base de datos
     * @param username Nombre de usuario
     * @param password Contraseña
     * @return true si el registro fue exitoso, false en caso contrario
     */
    public boolean registrarUsuario(String username, String password) {
        conexioOracle conexion = new conexioOracle();
        Connection conn = conexion.getConn();
        boolean registroExitoso = false;

        try {
            // Primero verificamos si el usuario ya existe
            String sqlCheck = "SELECT COUNT(*) FROM Usuario WHERE username = ?";
            PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck);
            stmtCheck.setString(1, username);
            ResultSet rsCheck = stmtCheck.executeQuery();

            rsCheck.next();
            int count = rsCheck.getInt(1);

            if (count > 0) {
                // El usuario ya existe
                System.out.println("El usuario ya existe en la base de datos");
                return false;
            }

            // Obtener el siguiente ID disponible
            String sqlMaxId = "SELECT NVL(MAX(idUsuario), 0) + 1 FROM Usuario";
            PreparedStatement stmtMaxId = conn.prepareStatement(sqlMaxId);
            ResultSet rsMaxId = stmtMaxId.executeQuery();

            int nuevoId = 1;
            if (rsMaxId.next()) {
                nuevoId = rsMaxId.getInt(1);
            }

            // Insertar el nuevo usuario
            String sqlInsert = "INSERT INTO Usuario (idUsuario, username, password) VALUES (?, ?, ?)";
            PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert);
            stmtInsert.setInt(1, nuevoId);
            stmtInsert.setString(2, username);
            stmtInsert.setString(3, password);

            int filasAfectadas = stmtInsert.executeUpdate();

            if (filasAfectadas > 0) {
                registroExitoso = true;
            }

            // Cerrar recursos
            stmtInsert.close();
            rsMaxId.close();
            stmtMaxId.close();
            rsCheck.close();
            stmtCheck.close();

        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
        } finally {
            // Cerrar la conexión
            conexion.desconectar();
        }

        return registroExitoso;
    }
}