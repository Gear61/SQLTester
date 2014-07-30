package com.sqltester;

import java.util.*;

// Class for serving the tables to be created to the MySQLiteHelper
public class SchemaServer {
    // Our singleton
    public static SchemaServer instance = null;

    // Hardcoded table names + columns here
    // TABLE NAMES
    private String[] tableNames = {"COMPLETION_STATUS", "SALARIES"};

    // PERSISTENT TABLE NAMES (THOSE THAT ARE NEVER "RENEWED")
    private String[] persistentNames = {"COMPLETION_STATUS"};

    // COLUMNS FOR THE TABLES
    private Column[][] tableColumns =
            {
                    {
                            new Column("Question_Number", "TEXT"),
                    },
                    {
                            new Column("Professor_Name", "TEXT"),
                            new Column("Department", "TEXT"),
                            new Column("Salary", "INT")
                    }
            };

    // Array of all our schemas
    private Schema[] allSchemas = new Schema[tableNames.length];

    public static SchemaServer getSchemaServer()
    {
        if (instance == null) {
            instance = new SchemaServer();
        }
        return instance;
    }

    private SchemaServer()
    {
        for (int i = 0; i < tableNames.length; i++)
        {
            allSchemas[i] = new Schema(tableNames[i], tableColumns[i], RowServer.getRows(i));
        }
    }

    // Return a table based on position
    public Schema serveTable(int position) {
        return allSchemas[position];
    }

    // Return a table based on name
    public Schema serveTable(String tableName)
    {
        for (int i = 0; i < tableNames.length; i++)
        {
            if (allSchemas[i].getName().equals(tableName))
            {
                return allSchemas[i];
            }
        }
        return null;
    }

    // Creates and serves all tables
    public Schema[] serveAllTables() {
        return allSchemas;
    }

    // Serve all non-persistent table names (presumably so they can be updated)
    public String[] serveNPTables()
    {
        Set<String> TableNamesSet = new HashSet(Arrays.asList(tableNames));
        Set<String> persistentNamesSet = new HashSet(Arrays.asList(persistentNames));

        String[] NPTables = {};
        TableNamesSet.removeAll(persistentNamesSet);
        NPTables = TableNamesSet.toArray(NPTables);
        return NPTables;
    }
}
