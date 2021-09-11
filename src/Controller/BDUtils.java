package Controller;

import java.sql.*;
import java.time.LocalDate;

public class BDUtils {

    protected static final String MYSQLDB = "jdbc:mysql://localhost:3306/Preguntas";
    protected static final String MYSQLDB_USER = "root";
    protected static final String MYSQLDB_PASSWORD = "root";

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        BDUtils.connection = connection;
    }

    public static void Connection() throws SQLException {
        connection = DriverManager.getConnection(MYSQLDB, MYSQLDB_USER, MYSQLDB_PASSWORD);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    public static int setGuardar(String Player, LocalDate Fecha, int Score, int Level) throws SQLException {
        String sqlPlayer = "INSERT INTO `HISTORIAL` (`Player`, `Date`, `Score`, `Level`) "
                + "VALUES ('" + Player + "','" + Fecha + "'," + Score + "," + Level + ")";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlPlayer);
        return preparedStatement.executeUpdate();
    }

    public static void desconectar() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public static void GetHistorial() throws SQLException {
        try {
            Connection();
            resultSet = statement.executeQuery("SELECT * FROM `HISTORIAL`");
            System.out.println("****** Historial ******");
            while (resultSet.next()) {
                System.out.println("ID : " + resultSet.getString(1));
                System.out.println("Jugador : " + resultSet.getString(2));
                System.out.println("Fecha De Ingreso : " + resultSet.getString(3));
                System.out.println("Puntuacion : " + resultSet.getString(4));
                System.out.println("Nivel : " + resultSet.getString(5));
                System.out.print("____________________________________\n");
            }

        } catch (SQLException ignored) {
        } finally {
            BDUtils.desconectar();
        }
    }
}
