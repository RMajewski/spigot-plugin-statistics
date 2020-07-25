package de.rene_majewski.mc.spigot.plugin.statistics.database.tables;

/**
 * Von dieser Klasse werden die einzelnen Datenbank Tabellen abgeleitet.
 *
 * @author René Majewski
 * @since 0.1
 */
public abstract class Table {
    /**
     * Name für der Datenbank-Tabelle mit den Version-Informationen.
     */
    public static final String TABLE_NAME_INFORMATIONS = "informations";

    /**
     * Speichert die Datenbank-Version der Klasse.
     */
    private int mDbVersion;

    /**
     * Speichert den Namen der Tabelle in der Datenbank.
     */
    private String mTableName;

    /**
     * Speichert den Suffix des Tabellen-Namens in der Datenbank.
     */
    private String mTableSuffix;

    /**
     * Initialisiert diese Klasse.
     *
     * @param version Version dieser Klasse.
     *
     * @param tableName Name der Tabelle in der Datenbank.
     *
     * @param tableSuffix Suffix des Tabellen-Namens.
     */
    public Table(final int version, final String tableName,
                 final String tableSuffix) {
        this.mDbVersion = version;
        this.mTableName = tableName;
        this.mTableSuffix = tableSuffix;
    }

    /**
     * Gibt die Version dieser Datenbank-Tabelle zurück.
     *
     * @return Version der Datenbank-Tabelle.
     */
    public int getVersion() {
        return this.mDbVersion;
    }

    /**
     * Gibt den Namen der Tabelle in der Datenbank zurück.
     *
     * @return Name der Tabelle in der Datenbank.
     */
    public String getTableName() {
        return this.mTableName;
    }

    /**
     * Gibt den Suffix des Tabellen-Namens in der Datenbank zurück.
     *
     * @return Suffix des Tabellen-Namens in der Datenbank.
     */
    public String getTableSuffix() {
        return this.mTableSuffix;
    }

    /**
     * Gibt den vollständigen Namen der Tabelle in der Datenbank zurück.
     *
     * @return Vollständiger Name der Tabelle in der Datenbank.
     */
    public String getFullTableName() {
        return this.mTableSuffix + this.mTableName;
    }


    /**
     * Erzeugt die Tabelle oder die Updates der Tabelle in der Datenbank.
     *
     * Als erstes ermittelt welche Version in der Datenbank der Tabelle
     * vorhanden ist. Wenn noch keine Version in der Datenbank erzeugt wurde,
     * so wird die @link #createTable aufgerufen
     */
    public void generateDatabase() {

    }


    protected void createTable() {
    }

    /**
     * Ermittelt die Version der Tabelle in der Datenbank.
     *
     * @return Version der Tabelle in der Datenbank. Wenn ein Fehler
     * aufgetreten ist, wird <b>-1</b> zurück gegeben.
     */
    protected int getVersionInDatabase() {
        int result = -1;



        return result;
    }

    /**
     * Generiert den SQL-Befehl zum Abfragen der Tabellen-Version in der
     * Datenbank.
     *
     * @return SQL-Befehl zum Abfragen der Tabellen-Version in der Datenbank.
     */
    protected String sqlGetVersionInDatabase() {
        StringBuffer result = new StringBuffer();

        result.append("SELECT version FROM ");

        if (this.mTableSuffix != null && !this.mTableSuffix.isEmpty()) {
            result.append(this.mTableSuffix);
        }

        result.append(TABLE_NAME_INFORMATIONS);
        result.append(" WHERE table_name = `");
        result.append(this.getFullTableName());
        result.append("`");

        return result.toString();
    }
}
