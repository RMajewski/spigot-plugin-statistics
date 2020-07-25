package de.rene_majewski.mc.spigot.plugin.statistics.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.crypto.Data;

/**
 * Klasse die den Zugriff auf die Datenbank managed.
 * 
 * @author René Majewski
 * 
 * @since 0.1
 */
public class Database {
    /**
     * Speichert die Instanz der Klasse.
     */
    private static Database _instance;

    /**
     * Speichert die Verbindung zur Datenbank.
     */
    private Connection _connection;

    /**
     * Konstruktor
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
        if (Database._instance == null) {
            Database._instance = new Database();
        }

        return Database._instance;
    }

    /**
     * Öffnet die Verbindung zur Datenbank
     * 
     * @param host URL des Server, auf dem der MySQL-Server läuft
     * 
     * @param port Port über den der MySQL-Server erreichbar ist
     * 
     * @param name Name der Datenbank, auf die Zugegriffen werden soll.
     * 
     * @param user Name des Datenbank-Benutzers.
     * 
     * @param password Password für den Datenbank-Benutzer.
     */
    public void open(String host, int port, String name, String user, String password) {
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

            this._connection = DriverManager.getConnection(url.toString(), user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Beendet die Verbindung zur Datenbank
     */
    public void close() {
        if (this._connection != null) {
            try {
                this._connection.close();
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
}