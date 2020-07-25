package de.rene_majewski.mc.spigot.plugin.statistics.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Klasse die den Zugriff auf die Datenbank managed.
 *
 * @author René Majewski
 *
 * @since 0.1
 */
public final class Database {
    /**
     * Gibt den Standard-Port an, auf dem der MySQL-Server läuft.
     */
    public static final int STANDARD_PORT = 3306;

    /**
     * Speichert die Instanz der Klasse.
     */
    private static Database mInstance;

    /**
     * Speichert die Verbindung zur Datenbank.
     */
    private Connection mConnection;

    /**
     * Konstruktor.
     */
    private Database() {
    }

    /**
     * Gibt die Instanz der Klasse zurück.
     *
     * Wenn die Instanz der Klasse noch nicht existiert wird diese erzeugt.
     *
     * @return Instanz dieser Klasse.
     */
    public static Database getInstance() {
        if (Database.mInstance == null) {
            Database.mInstance = new Database();
        }

        return Database.mInstance;
    }

    /**
     * Öffnet die Verbindung zur Datenbank.
     *
     * @param host URL des Server, auf dem der MySQL-Server läuft.
     *
     * @param port Port über den der MySQL-Server erreichbar ist.
     *
     * @param name Name der Datenbank, auf die Zugegriffen werden soll.
     *
     * @param user Name des Datenbank-Benutzers.
     *
     * @param password Password für den Datenbank-Benutzer.
     */
    public void open(final String host, final int port, final String name,
                     final String user, final String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            StringBuffer url = new StringBuffer();
            url.append("jdbc:mysql://");
            url.append(host);
            url.append(":");
            url.append(port);
            url.append("/");
            url.append(name);

            System.out.println(url.toString());

            this.mConnection = DriverManager.getConnection(
                url.toString(),
                user,
                password
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Beendet die Verbindung zur Datenbank.
     */
    public void close() {
        if (this.mConnection != null) {
            try {
                this.mConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Erzeugt die Datenbankstruktur.
     */
    public void createDatabase() {

    }

    /**
     * Führt einen SQL-Befehl aus.
     *
     * @param sql SQL-Befehl der ausgeführt werden soll.
     *
     * @return Resultat des SQL-Befehls. Ist ein Fehler aufgetreten, so wird
     * <code>null</code> zurück gegeben.
     */
    public ResultSet execQuery(final String sql) {
        ResultSet result = null;

        try {
            PreparedStatement stm =  this.mConnection.prepareStatement(sql);
            result = stm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
