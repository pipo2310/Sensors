package ucr.ac.ecci.ci1340.InputSimulator.DBMS;

import org.javatuples.Triplet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MySQLExecutor {

    private Connection connection;
    private MySQLConfiguration mySQLConfiguration;

    public MySQLExecutor(MySQLConfiguration mySQLConfiguration) {
        this.mySQLConfiguration = mySQLConfiguration;
    }

    /**
     * Open a connection with the MySQL database, using the values stored on the MySQLConfiguration class
     */
    public void open() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + mySQLConfiguration.getIp() + ":" + mySQLConfiguration.getPort() + "/" + mySQLConfiguration.getSchema();
            connection = DriverManager.getConnection(url, mySQLConfiguration.getUser(), mySQLConfiguration.getPassword());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts a log on the sensores_logs table
     * @param log triplet with the sensor id as first value, the datetime as second and the measure as third
     */
    public void insertLog(Triplet<String, LocalDateTime, Double> log) {
        try {
            Statement statement = connection.createStatement();
            String insert = "INSERT INTO sensores_logs VALUES ( '" + log.getValue0() + "' , " + log.getValue1().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ", " + log.getValue2() + ");";
            statement.executeUpdate(insert);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the connection
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}