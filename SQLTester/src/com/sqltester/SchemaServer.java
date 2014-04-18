package com.sqltester;

// Class for serving the tables to be created to the MySQLiteHelper
public class SchemaServer
{
	// Our singleton
	public static SchemaServer instance = null;
	
	// Hardcoded table names + columns here
	// TABLE NAMES
	private String[] tableNames = {"COMPLETION_STATUS", "SALARIES"};
	
	// COLUMNS
	private Column[][] tableColumns = {
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
		if (instance == null)
		{
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
	
	public Schema serveTable(int position)
	{
		return allSchemas[position];
	}
	
	// Creates and serves all tables
	public Schema[] serveAllTables ()
	{
		return allSchemas;
	}
}
