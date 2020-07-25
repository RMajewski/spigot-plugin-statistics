package de.rene_majewski.mc.spigot.plugin.statistics.database.tables;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Testet die Klasse @link TestTable
 * 
 * @author René Majewski
 * @since 0.1
 */
public class TableTest {
    /**
     * Speichert die zu testende Klasse.
     */
    private TestTable _test;

    /**
     * Speichert die Version der Test-Tabelle
     */
    private int _version;

    /**
     * Speichert den Namen der Test-Tabelle.
     */
    private String _tableName;

    /**
     * Speichert den Suffix des Namens der Test-Tabelle.
     */
    private String _tableSuffix;

    /**
     * Initialisiert die einzelnen Tests
     */
    @Before
    public void setUp() {
        this._version = 1;
        this._tableName = "test";
        this._tableSuffix = "suffix_";

        initTable();
    }

    /**
     * Räumt nach jeden Test auf.
     */
    @After
    public void tearDown() {
        _test = null;
    }

    /**
     * Initialisiert die Test Tabelle.
     */
    private void initTable() {
        this._test = new TestTable(this._version, this._tableName, this._tableSuffix);
    }
    
    @Test
    public void testSqlGetVersionInDatabaseWithNoTableSuffix() {
        this._tableSuffix = "";
        initTable();

        String expected = "SELECT version FROM informations WHERE table_name = `test`";
        assertEquals(expected, this._test.sqlGetVersionInDatabase());
    }
    
    @Test
    public void testSqlGetVersionInDatabaseWithTableSuffix() {
        String expected = "SELECT version FROM suffix_informations WHERE table_name = `suffix_test`";
        assertEquals(expected, this._test.sqlGetVersionInDatabase());
    }
}
