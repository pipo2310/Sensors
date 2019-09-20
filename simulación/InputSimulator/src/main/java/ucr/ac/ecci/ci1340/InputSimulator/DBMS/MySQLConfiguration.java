package ucr.ac.ecci.ci1340.InputSimulator.DBMS;

public class MySQLConfiguration {
    private String ip;
    private String port;
    private String user;
    private String password;
    private String schema;

    public MySQLConfiguration(String ip, String port, String user, String password, String schema) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.password = password;
        this.schema = schema;
    }

    String getIp() {
        return ip;
    }

    String getPort() {
        return port;
    }

    String getUser() {
        return user;
    }

    String getPassword() {
        return password;
    }

    String getSchema() {
        return schema;
    }
}
