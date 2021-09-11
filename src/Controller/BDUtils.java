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

    private static ResultSet ID = null;

    public static void Connection() throws SQLException {
        connection = DriverManager.getConnection(MYSQLDB, MYSQLDB_USER, MYSQLDB_PASSWORD);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    public static int setGuardar(String Player,LocalDate Fecha,int Score,int Level) throws SQLException {
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

    public static void GetGanadores() throws SQLException {
        StringBuilder PrimerPuesto = new StringBuilder();
        StringBuilder SegundoPuesto = new StringBuilder();
        StringBuilder TercerPuesto = new StringBuilder();
        try {
            resultSet = statement.executeQuery("SELECT * FROM `ganadores`");
            while (resultSet.next()) {
                switch (resultSet.getInt(3)) {
                    case 1:
                        PrimerPuesto.append(" ").append(resultSet.getString(2)).append("    ").append(resultSet.getInt(4)).append("\n");
                        break;

                    case 2:
                        SegundoPuesto.append(" ").append(resultSet.getString(2)).append("    ").append(resultSet.getInt(4)).append("\n");
                        break;

                    case 3:
                        TercerPuesto.append(" ").append(resultSet.getString(2)).append("    ").append(resultSet.getInt(4)).append("\n");
                        break;

                    default:
                        break;
                }
            }
            System.out.println("      Ganadores");
            System.out.println("    Primer Lugar");
            System.out.println("  Nombre    Cantidad\n");
            System.out.println(PrimerPuesto);
            System.out.println("    Segundo Lugar");
            System.out.println("  Nombre    Cantidad\n");
            System.out.println(SegundoPuesto);
            System.out.println("    Tercer Lugar ");
            System.out.println("  Nombre    Cantidad\n");
            System.out.println(TercerPuesto);
        } catch (SQLException ignored) {
        } finally {
            BDUtils.desconectar();
        }
    }
}
